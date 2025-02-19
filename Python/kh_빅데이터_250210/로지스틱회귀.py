# 로지스틱 회귀 : 입력값을 0과 1사이의 확률로 변환하는 시스모이드 함수를 사용해서
# 머신러닝과 통계학에서 자주 사용되는 분류 알고리즘 (이진 분류에 많이사용)

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler  # 데이터를 표준화하는 도구(평균을 0, 표준편차 1)
from sklearn.neighbors import KNeighborsClassifier

# 1.데이터 불러오기
fish = pd.read_csv('https://bit.ly/fish_csv_data')
# 물고기의 무게, 길이, 대각선 길이, 높이, 너비 정보를 선택
# 2.입력(x)과 타겟(y) 분리
fish_input = fish[['Weight', 'Length', 'Diagonal', 'Height', 'Width']].to_numpy()
fish_target = fish['Species'].to_numpy()  # 타깃인 물고기의 종류

# 3.학습 데이터와 테스트 데이터 분할
train_input, test_input, train_target, test_target = train_test_split(
    fish_input, fish_target, random_state=42)

# 4.데이터 표준화 (평균 0, 표준편차 1로 변환)
ss = StandardScaler()
ss.fit(train_input)
train_scaled = ss.transform(train_input)
test_scaled = ss.transform(test_input)

# 5.k-최근접 이웃 (K-NN) 분류기
kn = KNeighborsClassifier(n_neighbors=3)
kn.fit(train_scaled, train_target)

# K-NN 모델 성능 확인
print("============ K-NN 모델 성능 =========")
print(f"훈련 세트 정확도 : {kn.score(train_scaled, train_target)}")
print(f"테스트 세트 정확도 : {kn.score(test_scaled, test_target)}")
print(f"테스트 데이터 정확도 : {kn.predict(test_scaled[:5])}")
print(f"각 클래스의 확률 예측 : {kn.predict_proba(test_scaled[:5])}")