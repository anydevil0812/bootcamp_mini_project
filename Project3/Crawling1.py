# 국내 기후변화 의안 워드클라우드 및 단어 빈도수 TOP 15 그래프 생성 파일
import re
from bs4 import BeautifulSoup
import requests
import Cloud
from Cloud import MakeCloud
import plotly.graph_objects as go
import plotly.io as pio

# 공공데이터 포털 국회사무처_의안 정보 오픈 API를 이용하여 크롤링
url = 'http://apis.data.go.kr/9710000/BillInfoService2/getBillInfoList'
params ={'ServiceKey' : 'Ws7z6yKz623hbrHshOvor+6YEclNv4A2kyaiwVd97XGoZpsn2TS41ZzgqzThFlqYnOaIYoPDsErGGbjc1/QIog==',
         'numOfRows' : '50', 'pageNo' : '1',
         'ord' : 'A01', 'start_ord' : '18', 'end_ord' : '21', 'bill_name' : '기후'}
response = requests.get(url, params=params) # UTF-8 코드 형식로 데이터가 불러와짐
soup = BeautifulSoup(response.content.decode('utf-8'), 'xml') # 한글로 보기 편하게 디코딩
text = soup.select('summary') # summary 태그 안에 있는 내용만 추출

# 불필요한 텍스트 제거
text = str(text) # 정규식을 적용하기 위하여 bs4.element.ResultSet -> str로 변환
text = re.sub('([,.!?·？\"\'])', '', text) # 특수문자 제거
text = re.sub('<.+>', '', text) # <> 태그 제거

# 워드클라우드 생성
Cloud1 = MakeCloud()
Cloud1.wc(text,120,140,'WordCloud1.jpg')

# 단어 빈도수 TOP 15 그래프 생성
table = Cloud.table
table = table.loc[1:15,:]
pio.templates.default = "plotly_white"
graph = go.Bar(x=table['단어'], y=table['빈도수'], marker={"color":"green"})
layout = go.Layout(title='국내 기후변화 의안 단어 빈도수 TOP 15',font={'family':'Malgun Gothic', 'size':18},
                   xaxis={'title':'단어'},yaxis={'title':'빈도수'},width=600,height=700)
fig = go.Figure(data=graph, layout=layout)
fig.show()