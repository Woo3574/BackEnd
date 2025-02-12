# 결측치는 누락된 값 또는 비어 있는 값을 의미, 결측치가 있으면 분석 결과가 왜곡 됨
import pandas as pd
import numpy as np

df = pd.DataFrame({
    'sex': ['M', 'F', np.nan, 'M', 'F'],
    'score': [5, 4, 3, 4, np.nan]
})

print(df)
print(pd.isna(df)) # 결측치 확인
print(pd.isna(df).sum()) # 결측치 합계 출력

# 결측치 제거하기
print(df.dropna(subset=['score']))
print(df.dropna(subset=['score', 'sex']))

# 평균값으로 결측치 대체하기
exam = pd.read_csv('exam.csv')
# 데이터에 결측치 만들기
exam.loc[[2, 7, 14], ['math']] = np.nan # 2, 7, 14번의 행의 math 컬럼 값에 NaN 할당
print(exam)

print(exam['math'].mean()) # 수학 성적의 평균 값 구하기

exam['math'] = exam['math'].fillna(exam['math'].mean().round(0))
print(exam['math'].sum())

df2 = pd.DataFrame({
    'sex': [1, 2, 1, 3, 2, 1],
    'score': [5, 4, 3, 4, 3, 6]
})
print(df2)

# 이상치(잘못 들어온 값)를 결측치로 변환
df2['sex'] = np.where(df2['sex'] > 2, np.nan, df2['sex'])
df2['score'] = np.where(df2['score'] > 5, np.nan, df2['score'])
print(df2)