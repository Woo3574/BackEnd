import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# 1. 데이터 특징 파악
midwest = pd.read_csv('./midwest.csv')
print(midwest.head()) # 기본적으로 앞부분 5행 출력
print(midwest.info()) # 변수들의 속성을 보여 줌, 결측치도 확인 가능
print(midwest.describe()) # 요약 통계정보 출력

# 2. poptotal(전체 인구) 변수를 total로, popasian(아시아 인구) 변수를 asian으로 수정
midwest = midwest.rename(columns = { 'poptotal': 'total',  'popasian': 'asian'})
print(midwest.info())

# 3. 전체 인구 대비 아시아 인구 백분율
midwest['ratio'] = midwest['asian'] / midwest['total'] * 100
# print(midwest['ratio'].value_counts())
plt.rc('font', family = 'Malgun Gothic') # Winodw
# plt.rc('font', family = 'AppleGothic') # Mac
plt.hist(midwest['ratio'])
plt.xlabel('아시안인 비율 (%)')
plt.ylabel('빈도수')
plt.title('Midwest 지역의 아시아인 비율 분포')
plt.show()

# 4. 평균을 초과하면 large, 그 외에는 small
mean_ratio = midwest['ratio'].mean()
midwest['group'] = np.where(midwest['ratio'] > mean_ratio, 'large', 'small')
midwest['group'].value_counts().plot.bar(rot = 0)
plt.show()

# midwest['ratio'].mean()
# midwest['group'] = np.where(midwest['ratio'] > 0.4872, 'large', 'small')
# count_test = midwest['group'].value_counts()
# count_test.plot.bar(rot = 0)
# plt.show()
