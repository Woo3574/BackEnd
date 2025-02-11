import pandas as pd
exam = pd.read_csv('./exam.csv')
print(exam.head()) # 기본적으로 앞부분 5행 출력
print(exam.tail(3)) # 데이터의 마지막 3행 출력
print(exam.shape) # 데이터프레임의 크기를 보여줌
print(exam.info()) # 변수들의 속성을 보여 줌, 결측치도 확인 가능
print(exam.describe()) # 요약 통계정보 출력

