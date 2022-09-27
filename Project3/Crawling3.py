# 국내 기후변화 법령 워드클라우드 및 단어 빈도수 TOP 10 그래프 생성 파일

import glob # glob.glob() = 사용자가 제시한 조건에 맞는 파일명을 리스트 형식으로 반환
import os
import Cloud
from Cloud import MakeCloud
import plotly.graph_objects as go
import plotly.io as pio

os.chdir('C:/Users/playdata/Desktop/bootcamp_project/Project3/law_data')
read_files = glob.glob("*.txt") # glob.glob()을 이용하여 txt 파일 리스트 생성

with open('기후 관련 법률 합본.txt', 'wb') as file: # 법률 파일들을 '기후 관련 법률 합본' 이라는 하나의 txt 파일로 합치기
    for f in read_files:
        line = '***********' + f + '***********' + '\n\n'
        file.write(line.encode('utf-8'))
        with open(f,'rb') as infile:
            file.write(infile.read())
    file.close()
    infile.close()

with open('기후 관련 법률 합본.txt', 'r', encoding='utf-8') as f:
    text = f.read()

# 워드 클라우드 생성
os.chdir('C:/Users/playdata/Desktop/bootcamp_project/Project3')
Cloud1 = MakeCloud()
Cloud1.wc3(text)

f.close()
file.close()

# 국내 기후변화 법령 단어 빈도수 TOP 10 그래프 생성
table = Cloud.table
table = table.loc[1:15,:]
pio.templates.default = "plotly_white"
graph = go.Bar(x=table['단어'], y=table['빈도수'], marker={"color":"brown"})

layout = go.Layout(title='국내 기후변화 법령 단어 빈도수 TOP 15',font={'family':'Malgun Gothic', 'size':18},
                   xaxis={'title':'단어'},yaxis={'title':'빈도수'},width=600,height=700)
fig = go.Figure(data=graph, layout=layout)
fig.show()