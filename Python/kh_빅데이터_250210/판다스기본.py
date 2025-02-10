# 판다스 : 테이블 형태의 데이터를 쉽게 다룰수 있도록 설계된 파이썬 데이터 분석 라이브러리
# 데이터의 전처리, 탐색, 변환 및 시각화 등에 사용
# Series - 1차원 데이터 구조, 리스트와 유사하지만 인덱스(라벨)가 붙어있음
import numpy as np
import pandas as pd

s1 = pd.Series([10, 20, 30, 40, 50])
print(s1)
print(s1.index)
print(s1.values)

# pandas는 데이터 타입이 달라도 사용 가능
s2 = pd.Series(["a", "b", "c", 3.14, True, 1000, 2000, "seoul"])
print(s2)

# 결측치 임의로 생성
s3 = pd.Series([1, 2, 3, 4, np.nan, 5, 6, 7, np.nan, 9 ,10])
print(s3)

# 임의의 인덱스 생성
index_date = ['2024-02-01', '2024-02-02', '2024-02-03', '2024-02-04']
s4 = pd.Series([200, 220, np.nan, 300],index = index_date)
print(s4)

# 2차원 데이터 구조 : DataFrame, 2차원 표 형태의 데이터 구조
data = {
    '이름': ['민지', '하니', '해린', '혜인', '다니엘'],
    '수학': [90, 80, 70, 60, np.nan],
    '영어': [98, 100, np.nan, 89, 99]
}

df = pd.DataFrame(data)
print(df)
print(df.info()) # 데이터 타입 및 결측치 정보
print(df.describe()) # 수치형 데이터 요약 및 통계 제공

# 특정 행 및 열 추출

print(df['수학']) # 특정 열 추출
print(df.loc[0]) # 특정 행 추출
print(df.loc[1, '영어']) # 특정 값 추출

# 새로운 열 및 행 추가
df['국어'] = [100, 98, 98, 70, 45] # 새로운 열 추가
print(df)
df.loc[5] = ['박지숙', 67 ,45 ,30]
print(df)

# 기본 연산
print(df['수학'].sum())
print(df['수학'].mean())
print(df['수학'].max())
print(df['수학'].min())
