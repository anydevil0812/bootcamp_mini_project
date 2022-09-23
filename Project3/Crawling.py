# 공공데이터 포털 국회 국회사무처_의안 정보 오픈 API 크롤링
import requests
from xml.etree import ElementTree as ET
import re
from bs4 import BeautifulSoup
import matplotlib.pyplot as plt
import numpy as np
from pandas import DataFrame
import requests
from Cloud import MakeCloud

url = 'http://apis.data.go.kr/9710000/BillInfoService2/getBillInfoList'
params ={'ServiceKey' : 'Ws7z6yKz623hbrHshOvor+6YEclNv4A2kyaiwVd97XGoZpsn2TS41ZzgqzThFlqYnOaIYoPDsErGGbjc1/QIog==',
         'numOfRows' : '50', 'pageNo' : '1',
         'ord' : 'A01', 'start_ord' : '18', 'end_ord' : '21', 'bill_name' : '기후'}

response = requests.get(url, params=params) # UTF-8 코드 형식로 데이터가 불러와짐
final = BeautifulSoup(response.content.decode('utf-8'), 'xml') # 한글로 보기 편하게 디코딩
final_text = final.select('summary') # summary 태그 안에 있는 내용만 추출

print(final_text)

Cloud1 = MakeCloud()

Cloud1.wc(str(final_text))