# 2020~2022 국내 기후변화&코로나 대응책 의안수 선 그래프 생성 파일
import re
from bs4 import BeautifulSoup
import requests
import pandas as pd
import plotly.graph_objects as go
import plotly.io as pio

# 각 키워드별 2020,2021,2022년 의안수 리스트 생성 함수
def count_list(keyword, page_num):

    url = 'http://apis.data.go.kr/9710000/BillInfoService2/getBillInfoList'
    params ={'ServiceKey' : 'Ws7z6yKz623hbrHshOvor+6YEclNv4A2kyaiwVd97XGoZpsn2TS41ZzgqzThFlqYnOaIYoPDsErGGbjc1/QIog==',
             'numOfRows' : '100', 'pageNo' : page_num, 'ord' : 'A01',
             'start_ord' : '21', 'end_ord' : '21', 'bill_name' : keyword}
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

    year = sorted(list(dict.fromkeys(propose_year).keys())) # 중복 제거 후 오름차순 정렬

    propose_count = []
    for j in year:
        propose_count.append(propose_year.count(j))

    if '2020' not in year:
        year.insert(0,'2020')
        propose_count.insert(0,0)
    if '2021' not in year:
        year.insert(1,'2021')
        propose_count.insert(1, 0)
    if '2022' not in year:
        year.insert(2,'2022')
        propose_count.insert(2, 0)

    return propose_count

# 키워드별 각 연도별 의안수를 모두 더한 최종 의안수 리스트 생성 함수
def make_final_countlist(keyword, num):
    for i in range(1,num):
        count.append(count_list(keyword, i))
    return count

# 그래프 생성 함수
def make_graph(count, keyword, range, color):
    data = {"제안 연도": ['2020', '2021', '2022'], "의안수": count}
    table = pd.DataFrame(data)

    # 국내 기후변화 의안수 선 그래프 생성
    pio.templates.default = "plotly_white"
    graph = go.Scatter(x=table['제안 연도'], y=table['의안수'], line={'color': color, 'width': 2})
    layout = go.Layout(title=keyword+'에 관한 대한민국 의안 건수', font={'family': 'Malgun Gothic', 'size': 20},
                       xaxis={'title': '제안 연도'}, yaxis={'title': '의안수'}, width=600, height=700)
    fig = go.Figure(data=graph, layout=layout)
    fig.update_yaxes(range=[0, range])
    fig.show()

# 2020~2022년 국내 기후변화 의안수 그래프 생성
make_graph(count_list('기후',1),'기후변화',15,'red')

# 2020년~2022년 국내 코로나 의안수 그래프 생성
count = []
make_final_countlist('감염병',3)
make_final_countlist('의료',5)
make_final_countlist('코로나',2)
make_final_countlist('검역법',2)

final_count = [0,0,0]
for li in count:
    for j in range(len(final_count)):
        final_count[j] += li[j]

make_graph(final_count,'코로나',250,'black')



