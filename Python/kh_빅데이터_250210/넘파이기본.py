# 넘파이? 파이썬에서 과학적인 계산을 위한 핵심 라이브러리, 아나콘다를 사용하면 기본 포함
# - 다차원 배열 객체 제공, 고성능 수학 연산 지원
import numpy as np # np라는 별칭 부여

# 리스트와 튜플은 인덱스로 접근, 딕셔너리는 키로
data = [0, 1, 2, 3, 4, 5] # [] 리스트, {} 딕셔너리(키와 밸류), () 튜플
a1  = np.array(data)  # 리스트를 numpy 배열로 변환
print(a1)

# 정수와 실수가 혼합된 경우는 전부 실수로 변환
data2 = [0, 1, 2, 3.14, 4, 5.5, 6, 7, 8.99]
a2 = np.array(data2)
print(a2)

# 배열의 속성 확인
x = np.array([0.1, 0.2, 0.3])
print(x.shape) # 배열의 형태 (3행,)
print(x.dtype)

y = np.array(([1, 2, 3], [4, 5, 6]))
print(y.shape)
print(y.dtype)

# 특정 범위의 배열 생성
a3 = np.arange(0, 10, 2) # 0 ~ 10 미만, 간격을 2
print(a3)

a4 = np.arange(10) # 0 ~ 10 미만 , 간격 1
print(a4)

# 2차원 배열 생성
a5 = np.arange(12).reshape(4,3)
print(a5)
print(a5.shape)

# 주어진 범위를 3번째 값으로 일정한 간격으로 삽입
a6 = np.linspace(1, 10)
print(a6)

# 특정한 숫자로 채워진 배열 생성
a7 = np.zeros(10)
print(a7)

a8 = np.zeros((3, 4))
print(a8)

a9 = np.ones(10)
print(a9)

a10 = np.ones((5, 5))
print(a10)

a11 = np.eye(5) # 5 x 5 단위 행렬
print(a11)

# 배열의 데이터 타입
a12 = np.array(['1.5', '0.44', '3.14', '3.145992'])
print(a12)
print(a12.dtype)

num_a12 = a12.astype(float)
print(num_a12)

a13 = np.array(["1", "2", "3", "4", "5", "6", "7"])
num_a13 = a13.astype(int)
print(num_a13)