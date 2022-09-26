import re
from bs4 import BeautifulSoup
import requests
from Cloud import MakeCloud

# 공공데이터 포털 국회 국회사무처_의안 정보 오픈 API 크롤링
url = 'http://apis.data.go.kr/9710000/BillInfoService2/getBillInfoList'
params ={'ServiceKey' : 'Ws7z6yKz623hbrHshOvor+6YEclNv4A2kyaiwVd97XGoZpsn2TS41ZzgqzThFlqYnOaIYoPDsErGGbjc1/QIog==',
         'numOfRows' : '50', 'pageNo' : '1',
         'ord' : 'A01', 'start_ord' : '18', 'end_ord' : '21', 'bill_name' : '기후'}

# OPEN API를 이용하여 데이터 추출
response = requests.get(url, params=params) # UTF-8 코드 형식로 데이터가 불러와짐
soup = BeautifulSoup(response.content.decode('utf-8'), 'xml') # 한글로 보기 편하게 디코딩
unprocessed_text = soup.select('summary') # summary 태그 안에 있는 내용만 추출

# 불필요한 텍스트 제거
unprocessed_text = str(unprocessed_text) # 정규식을 적용하기 위하여 bs4.element.ResultSet -> str로 변환
unprocessed_text = re.sub('([,.■!?·？\"\'])', '', unprocessed_text) # 특수문자 제거
final_text = re.sub('<.+>', '', unprocessed_text) # <> 태그 제거
# print(final_text)

# # 텍스트 파일 작성
file = open('final_text.txt', 'w')
file.write(final_text)
file.close()

# 워드클라우드 파일 생성
Cloud1 = MakeCloud()
Cloud1.wc(final_text)

