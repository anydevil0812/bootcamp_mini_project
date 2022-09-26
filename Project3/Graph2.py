# 공공데이터 포털 국회 국회사무처_의안 정보 오픈 API 크롤링
import re
from bs4 import BeautifulSoup
import requests
import pandas as pd
import plotly.graph_objects as go
import plotly.io as pio

url = 'http://apis.data.go.kr/9710000/BillInfoService2/getBillInfoList'
params ={'ServiceKey' : 'Ws7z6yKz623hbrHshOvor+6YEclNv4A2kyaiwVd97XGoZpsn2TS41ZzgqzThFlqYnOaIYoPDsErGGbjc1/QIog==',
         'numOfRows' : '100', 'pageNo' : '1',
         'ord' : 'A01', 'start_ord' : '18', 'end_ord' : '21', 'bill_name' : '기후'}

# OPEN API를 이용하여 데이터 추출
response = requests.get(url, params=params) # UTF-8 코드 형식로 데이터가 불러와짐
soup = BeautifulSoup(response.content.decode('utf-8'), 'xml') # 한글로 보기 편하게 디코딩

propose_date = soup.select('proposeDt')

# 불필요한 텍스트 제거
propose_date = str(propose_date) # 정규식을 적용하기 위하여 bs4.element.ResultSet -> str로 변환
rule1 = re.compile('(?<=\<proposeDt>)(.*?)(?=<\/proposeDt>)') # rule에 정규식 표현식 컴파일
date = rule1.findall(propose_date)

propose_year = []
for i in date:
    j = i.split('-')[0]
    propose_year.append(j)

vacant_year = ['2010','2015','2018']
propose_year.extend(vacant_year)
year = sorted(list(dict.fromkeys(propose_year).keys()))

propose_count = []
for j in year:
    if j in vacant_year:
        propose_count.append(0)
    else:
        propose_count.append(propose_year.count(j))

data = {"제안 연도":year, "의안수":propose_count}
table = pd.DataFrame(data)

pio.templates.default = "plotly_white"
graph = go.Scatter(x=table['제안 연도'], y=table['의안수'], line={'color':'red','width':2})

layout = go.Layout(title='기후변화에 관한 대한민국 의안 건수', font={'family':'Malgun Gothic', 'size':20},
                   xaxis={'title':'제안 연도'},yaxis={'title':'의안수'},width=600,height=700)
fig = go.Figure(data=graph, layout=layout)
fig.show()