import glob # glob.glob() = 사용자가 제시한 조건에 맞는 파일명을 리스트 형식으로 반환
import os

os.chdir('C:/Users/playdata/Desktop/MiniProject')

read_files = glob.glob("*.txt") # glob.glob()을 이용하여 txt 파일 리스트 생성

with open('기후 관련 법률 합본.txt', 'wb') as file: # 법률 파일들을 '기후 관련 법률 합본' 이라는 하나의 txt 파일로 합치기
    for f in read_files:
        line = '***********' + f + '***********' + '\n\n'
        file.write(line.encode('utf-8'))
        with open(f,'rb') as infile:
            file.write(infile.read())