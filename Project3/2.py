import urllib.request
import requests
from selenium import webdriver
import pandas as pd
from urllib.request import urlopen
from bs4 import BeautifulSoup
import os
import re
from tqdm import trange
import xml.etree.ElementTree as ET
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

URL = 'https://www.law.go.kr/DRF/lawService.do?OC=anydevil0812&target=law&MST=152338&type=HTML'
# url = urllib.request.urlopen(URL)

response = requests.get(URL)
html = response.text
soup = BeautifulSoup(html, 'html.parser')
# Xtree = ET.fromstring(response)
# html = response.text
# soup = BeautifulSoup(html, 'html.parser')
# texts = soup.select_one('#conScroll > div:nth-child(3) > div > p > span.bl')
#
# texts = soup.find_all("div")
print(soup)

# link = case_list.loc[i][''].replace('HTML','XML')
# url += link

# response = urlopen(URL).read()

# url = urlopen(URL)
# file = soup.select('law.txt')