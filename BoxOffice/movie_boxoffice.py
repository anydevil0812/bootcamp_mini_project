# 국내 박스오피스 순위 생성 파일
import re
from bs4 import BeautifulSoup
import requests
import pandas as pd
import plotly.graph_objects as go
import plotly.io as pio
from datetime import date, timedelta

# 5일간의 날짜 목록
date_list = []
for i in range(1,6):
    date_list.append(date.today()-timedelta(i))
date_list.sort()

# OPEN API 파라미터 입력을 위한 datetime -> str
date_list = list(map(str, date_list))
date_keyword = []
for i in range(len(date_list)):
    date_keyword.append(date_list[i].replace('-', ''))

keywords = ['rank', 'movieNm', 'openDt', 'audiCnt', 'audiAcc']

# 영화진흥위원회_영화박스오피스 DB OPEN API 이용하여 크롤링
def crawling_boxoffice(date, keyword):

    url = 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml'
    params ={'key' : 'ee0e8dd4b1074b2a1378799562c3d370', "targetDt" : date}
    response = requests.get(url, params=params) # UTF-8 코드 형식로 데이터가 불러와짐
    soup = BeautifulSoup(response.content, 'xml')

    factor = soup.select(keyword)
    factor = str(factor)

    if keyword == 'rank': # 순위
        rule = re.compile('(?<=\<rank>)(.*?)(?=<\/rank>)')
        final = rule.findall(factor)
        return final

    if keyword == 'movieNm': # 영화명
        rule = re.compile('(?<=\<movieNm>)(.*?)(?=<\/movieNm>)')
        final = rule.findall(factor)
        return final

    if keyword == 'openDt': # 개봉일
        rule = re.compile('(?<=\<openDt>)(.*?)(?=<\/openDt>)')
        final = rule.findall(factor)
        return final

    if keyword == 'audiCnt': # 일일 관객수
        rule = re.compile('(?<=\<audiCnt>)(.*?)(?=<\/audiCnt>)')
        final = rule.findall(factor)
        final = list(map(int, final))
        return final

    if keyword == 'audiAcc': # 누적 관객수
        rule = re.compile('(?<=\<audiAcc>)(.*?)(?=<\/audiAcc>)')
        final = rule.findall(factor)
        final = list(map(int, final))
        return final

data1 = {"순위" : crawling_boxoffice(date_keyword[0], 'rank'), "영화명" : crawling_boxoffice(date_keyword[0], 'movieNm'),
        "개봉일" : crawling_boxoffice(date_keyword[0], 'openDt'), "일일 관객수" : crawling_boxoffice(date_keyword[0], 'audiCnt'),
        "누적 관객수" : crawling_boxoffice(date_keyword[0], 'audiAcc'), "기준 날짜" : date_list[0]}

data2 = {"순위" : crawling_boxoffice(date_keyword[1], 'rank'), "영화명" : crawling_boxoffice(date_keyword[1], 'movieNm'),
        "개봉일" : crawling_boxoffice(date_keyword[1], 'openDt'), "일일 관객수" : crawling_boxoffice(date_keyword[1], 'audiCnt'),
        "누적 관객수" : crawling_boxoffice(date_keyword[1], 'audiAcc'), "기준 날짜" : date_list[1]}

data3 = {"순위" : crawling_boxoffice(date_keyword[2], 'rank'), "영화명" : crawling_boxoffice(date_keyword[2], 'movieNm'),
        "개봉일" : crawling_boxoffice(date_keyword[2], 'openDt'), "일일 관객수" : crawling_boxoffice(date_keyword[2], 'audiCnt'),
        "누적 관객수" : crawling_boxoffice(date_keyword[2], 'audiAcc'), "기준 날짜" : date_list[2]}

data4 = {"순위" : crawling_boxoffice(date_keyword[3], 'rank'), "영화명" : crawling_boxoffice(date_keyword[3], 'movieNm'),
        "개봉일" : crawling_boxoffice(date_keyword[3], 'openDt'), "일일 관객수" : crawling_boxoffice(date_keyword[3], 'audiCnt'),
        "누적 관객수" : crawling_boxoffice(date_keyword[3], 'audiAcc'), "기준 날짜" : date_list[3]}

data5 = {"순위" : crawling_boxoffice(date_keyword[4], 'rank'), "영화명" : crawling_boxoffice(date_keyword[4], 'movieNm'),
        "개봉일" : crawling_boxoffice(date_keyword[4], 'openDt'), "일일 관객수" : crawling_boxoffice(date_keyword[4], 'audiCnt'),
        "누적 관객수" : crawling_boxoffice(date_keyword[4], 'audiAcc'), "기준 날짜" : date_list[4]}
# data = {"순위" : crawling_boxoffice('rank'), "영화명" : crawling_boxoffice('movieNm'), "개봉일" : crawling_boxoffice('openDt'),
#         "일일 관객수" : crawling_boxoffice('audiCnt'), "누적 관객수" : crawling_boxoffice('audiAcc')}

table1 = pd.DataFrame(data1)


table2 = pd.DataFrame(data2)


table3 = pd.DataFrame(data3)


table4 = pd.DataFrame(data4)


table5 = pd.DataFrame(data5)


table = pd.concat([table1, table2, table3, table4, table5])
print(table)

number1 = crawling_boxoffice(date_keyword[4], 'movieNm')[0] # 어제 기준 박스오피스 1위 작품

final_table1 = table[table['영화명'] == number1]

print(final_table1)

# 국내 박스오피스 Top 5 선 그래프 생성
pio.templates.default = "plotly_dark"
graph = go.Scatter(x=final_table1['기준 날짜'], y=final_table1['일일 관객수'], line={'color':'red','width':2})
layout = go.Layout(title='국내 박스오피스 1위 영화 관객수 추이',font={'family':'Malgun Gothic', 'size':20},
                   xaxis={'title':'날짜'},yaxis={'title':'관객수'},width=1200,height=700)
fig = go.Figure(data=graph, layout=layout)
fig.update_layout(title_x=0.5) # 제목 가운데 정렬
fig.update_xaxes(dtick="D1", tickformat='%Y %m %d') # 날짜 Oct처럼 영어 안나오고 숫자로만 나오게 + 간격은 일간격으로
fig.update_yaxes(tickformat=',') # Y축 데이터 k없이 ,으로 간단하게 표시
fig.show()