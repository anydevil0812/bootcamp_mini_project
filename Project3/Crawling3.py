# 국내 기후변화 법령 워드클라우드 및 단어 빈도수 TOP 15 그래프 생성 파일
import glob
import os
import Cloud
from Cloud import MakeCloud
import plotly.graph_objects as go
import plotly.io as pio

# OPEN API로 크롤링 시도 -> 현행 법안만 크롤링 가능하고, 폐기된 법안은 권한으로 막아놔서 크롤링 불가라 패스
# 워드파일에서 바로 추출 시도 -> 워드파일의 형식이 docx면 가능한데 법령 워드파일은 구버전인 doc로만 되있어서 python3에서는 추출불가라 패스
# 결국 법령을 워드파일로 다운 -> 워드 파일의 내용을 메모장에 수동으로 붙여넣기 -> txt파일들에서 텍스트 추출하여 하나의 텍스트 파일 생성후 합침
os.chdir('C:/Users/playdata/Desktop/bootcamp_project/Project3/law_data')
read_files = glob.glob("*.txt")

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
Cloud3 = MakeCloud()
Cloud3.wc(text,0,15,'WordCloud3.jpg')
f.close()

# 국내 기후변화 법령 단어 빈도수 TOP 15 그래프 생성
table = Cloud.table
table = table.loc[1:15,:]
pio.templates.default = "plotly_white"
graph = go.Bar(x=table['단어'], y=table['빈도수'], marker={"color":"brown"})
layout = go.Layout(title='국내 기후변화 법령 단어 빈도수 TOP 15',font={'family':'Malgun Gothic', 'size':18},
                   xaxis={'title':'단어'},yaxis={'title':'빈도수'},width=600,height=700)
fig = go.Figure(data=graph, layout=layout)
fig.show()