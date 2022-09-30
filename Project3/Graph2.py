# 국내 기후변화 의안 처리결과 현황 막대&원그래프 생성 파일
import re
from bs4 import BeautifulSoup
import requests
import pandas as pd
import plotly.graph_objects as go
import plotly.io as pio
import matplotlib.pyplot as plt
import matplotlib

# 공공데이터 포털 국회사무처_의안 정보 오픈 API를 이용하여 크롤링
# 현재 국회의원인 21대 국회의원들이 발의한 의안은 심사진행상태 항목('procStageCd')을 이용하고
# 과거의 18~20대 국회의원들이 발의한한 의안은 의결결과 항목('generalResult')을 이용

url = 'http://apis.data.go.kr/9710000/BillInfoService2/getBillInfoList'
params1 ={'ServiceKey' : 'Ws7z6yKz623hbrHshOvor+6YEclNv4A2kyaiwVd97XGoZpsn2TS41ZzgqzThFlqYnOaIYoPDsErGGbjc1/QIog==',
         'numOfRows' : '50', 'pageNo' : '1',
         'ord' : 'A01', 'start_ord' : '18', 'end_ord' : '20', 'bill_name' : '기후'}

params2 ={'ServiceKey' : 'Ws7z6yKz623hbrHshOvor+6YEclNv4A2kyaiwVd97XGoZpsn2TS41ZzgqzThFlqYnOaIYoPDsErGGbjc1/QIog==',
         'numOfRows' : '50', 'pageNo' : '1',
         'ord' : 'A01', 'start_ord' : '21', 'end_ord' : '21', 'bill_name' : '기후'}

comp1 = 'generalResult'
comp2 = 'procStageCd'

# 데이터프레임 생성
def make_table(params, comp):

    # OPEN API를 이용하여 데이터 추출
    response = requests.get(url, params=params) # UTF-8 코드 형식로 데이터가 불러와짐
    soup = BeautifulSoup(response.content.decode('utf-8'), 'xml') # 한글로 보기 편하게 디코딩

    bill_name = soup.select('billName')
    bill_result = soup.select(comp) # 선택한 태그 안에 있는 내용만 추출

    # 불필요한 텍스트 제거
    bill_name = str(bill_name) # 정규식을 적용하기 위하여 bs4.element.ResultSet -> str로 변환
    bill_result = str(bill_result)

    rule1 = re.compile('(?<=\<billName>)(.*?)(?=<\/billName>)') # rule1에 정규식 표현식 컴파일
    p_bill_name = rule1.findall(bill_name)

    rule2 = re.compile('(?<=\<generalResult>)(.*?)(?=<\/generalResult>)') # rule2에 정규식 표현식 컴파일 - generalResult 태그 안에 있는 내용 모두 추출
    rule3 = re.compile('(?<=\<procStageCd>)(.*?)(?=<\/procStageCd>)')  # rule2에 정규식 표현식 컴파일 - procStageCd 태그 안에 있는 내용 모두 추출

    # 과거 18~20대에 발의된 추천안은 아예 결과가 없는 상태로 조회가 되어 해당 의안의 상태가 bill_result에 없어 bill_name에서만 삭제하면됨
    if comp == comp1:
        p_result = rule2.findall(bill_result)
        for i in p_bill_name:
            if i.find('추천') != -1 or i.find('추천의건') != -1:
                p_bill_name.remove(i)
        data = {"의안명": p_bill_name, "의결결과": p_result}
        global table1
        table1 = pd.DataFrame(data)

    # 현재 21대에 발의된 추천안은 결과가 나와있기 때문에 bill_result와 bill_name에서 모두 삭제해야함
    elif comp == comp2:
        p_result = rule3.findall(bill_result)
        for i in p_bill_name:
            if i.find('추천') != -1 or i.find('추천의건') != -1:
                index = p_bill_name.index(i)
                p_bill_name.remove(i)
                p_result.pop(index)
        data = {"의안명": p_bill_name, "의결결과": p_result}
        global table2
        table2 = pd.DataFrame(data)

make_table(params1, comp1)
make_table(params2, comp2)

# 데이터프레임 의결결과 컬럼 데이터 전처리
table = pd.concat([table1,table2])
table.reset_index(drop=True, inplace=True)

table.loc[table['의결결과'] =='원안가결', '의결결과'] = "가결"
table.loc[table['의결결과'] =='본회의의결', '의결결과'] = "가결"
table.loc[table['의결결과'] =='공포', '의결결과'] = "가결"

table.loc[table['의결결과'] =='임기만료폐기', '의결결과'] = "폐기"
table.loc[table['의결결과'] =='대안반영폐기', '의결결과'] = "폐기"
table.loc[table['의결결과'] =='철회', '의결결과'] = "폐기"

table.loc[table['의결결과'] =='소관위접수', '의결결과'] = "접수 및 심사"
table.loc[table['의결결과'] =='소관위심사', '의결결과'] = "접수 및 심사"

# 국내 기후변화 의안 처리결과 현황 막대&원 그래프 생성
result = table['의결결과'].unique()
num1 = len(table.loc[table['의결결과'] =='가결'])
num2 = len(table.loc[table['의결결과'] =='폐기'])
num3 = len(table.loc[table['의결결과'] =='접수 및 심사'])
count_list = [num1,num2,num3]
graph_data = {'처리결과':result, '제안 건수':count_list}
final_table = pd.DataFrame(graph_data)

# plotly를 이용하여 막대 그래프 생성
pio.templates.default = "plotly_white"
graph1 = go.Bar(x=final_table['처리결과'], y=final_table['제안 건수'], marker={"color":["red","aqua","orange"]})
layout = go.Layout(title='기후변화에 관한 대한민국 의안 처리결과 현황',font={'family':'Malgun Gothic', 'size':18},
                   xaxis={'title':'처리결과'},yaxis={'title':'제안 건수'},width=600,height=700)
fig = go.Figure(data=graph1, layout=layout)
fig.show()

# matplotlib을 이용하여 원 그래프 생성
matplotlib.rcParams['font.family'] ='Malgun Gothic'
matplotlib.rcParams['axes.unicode_minus'] = False
graph2 = plt.pie(final_table['제안 건수'], explode=(0.05,0.05,0.05), autopct='%1.2f%%', labels=final_table['처리결과'],
                startangle = 90, textprops = {'fontsize': 12}, colors=["red","aqua","orange"])
plt.title('기후변화에 관한 대한민국 의안 처리결과 현황 비율', fontsize=18)
plt.show()


