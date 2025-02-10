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

#  난수 배열의 생성
# rand() : 0 ~ 1 미만의 실수를 난수 배열을 생성
# a14 = np.random.rand(2, 3)
a14 = (np.random.rand(6) * 45) + 1
print(a14.astype(int))

# 지정한 범위에 해당 하는 정수로 난수 배열을 생성 : randint()
a15 = np.random.randint(10, size=(5, 10)) # 0 ~ 9 사이의 임의이 값을 사이즈 만큼 생성
print(a15)

# numPy 기본 연산 : 각 요소끼리 연산이 가능
arr1 = np.array([1, 2, 3])
arr2 = np.array([4, 5, 6])

print(arr1 + arr2)
print(arr1 - arr2)
print(arr1 * arr2)
print(arr1 / arr2)
print(arr1 ** 2)
print(arr2 > 5)

# 통계 연산
arr3 = np.arange(10) # 0 ~ 9까지 데이터 생성
print(f"합계 : {arr3.sum()}")
print(f"평균 : {arr3.mean()}")  # sum(arr3) / len(arr3)
print(f"표준편차 : {arr3.std()}")
print(f"분산 : {arr3.var()}")
print(f"최대값 : {arr3.max()}")
print(f"최대값 : {arr3.min()}")

arr4 = np.array([9, 7, 6, 12, 3, 45, 18, 1])
print(np.sort(arr4)) # 오름차순 정렬
print(np.argsort(arr4)) # 정렬된 인덱스 반환

arr5 = np.array([1, 2, 3, 4, 5, 6, 7, 8, 9])
# 인덱싱
print(arr5[6])
# 슬라이싱
print(arr5[5:]) # 6, 7, 8, 9
print(arr5[2:6]) # 3, 4, 5, 6

# 1번 문제 : 1부터 10까지의 숫자로 이루어진 1차원 배열을 생성하고, 모든 요소에 5를 더한 결과를 출력하세요.
ar1 = np.arange(1, 10)
print(ar1 + 5)

# 2번 문제 : 1부터 9까지의 숫자를 사용하여 3x3 크기의 2차원 배열을 생성하고 출력하세요.
ar2 = np.arange(9).reshape(3, 3)
print(ar2)

# 3번 문제 :
# 1부터 20까지의 숫자로 이루어진 배열을 생성하고, 다음을 계산하세요.
# 1.	배열의 합계
# 2.	배열의 평균
# 3.	배열의 최댓값과 최솟값
ar3 = np.arange(1,21)
print(f"배열의 합계 : {ar3.sum()}")
print(f"배열의 평균 : {ar3.mean()}")
print(f"배열의 최댓값 : {ar3.max()}")
print(f"배열의 최솟값 : {ar3.min()}")

# 4번 문제 : 0 에서 100 사이의 난수를 10개 생성 하고, 50 이상인 값을 출력
ar4 = np.random.randint(0, 101, size = 10)

filtered_values = ar4[ar4 > 50]  # 50보다 큰 값만 선택
print("50보다 큰 값:", filtered_values)