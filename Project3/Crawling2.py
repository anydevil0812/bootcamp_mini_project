# 국내 기후변화 보고서 워드클라우드 및 단어 빈도수 TOP 10 그래프 생성 파일

from PyPDF2 import PdfFileReader # PyPDF2 = PDF파일 텍스트 추출 패키지
import re
import Cloud
from Cloud import MakeCloud
import plotly.graph_objects as go
import plotly.io as pio

# 보고서 PDF파일로부터 텍스트 추출
pdf = PdfFileReader(open('2020.pdf','rb'))
with open('보고서 내용.txt','wb') as file:
    for i in range(5,111):
        pdf_text = re.sub('([,.!•→?○*＊·・↓？\"\'])', '', str(pdf.pages[i].extractText().split('\n')))
        pdf_text = pdf_text.replace('u3000', '')
        pdf_text = pdf_text.replace('u3000500', '')
        file.write(pdf_text.encode('utf-8'))
    with open('보고서 내용.txt', 'r', encoding='utf-8') as f:
        text = f.read()

# 워드 클라우드 생성
Cloud1 = MakeCloud()
Cloud1.wc2(text)

f.close()
file.close()

# 단어 빈도수 TOP 10 그래프 생성
table = Cloud.table
table = table.loc[1:15,:]
pio.templates.default = "plotly_white"
graph = go.Bar(x=table['단어'], y=table['빈도수'], marker={"color":"navy"})

layout = go.Layout(title='국내 기후변화 보고서 단어 빈도수 TOP 15',font={'family':'Malgun Gothic', 'size':18},
                   xaxis={'title':'단어'},yaxis={'title':'빈도수'},width=600,height=700)
fig = go.Figure(data=graph, layout=layout)
fig.show()

