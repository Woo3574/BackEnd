# 데이터 전처리 : 주어진 데이터를 분석에 적합하도록 가공하는 작업
import numpy as np
import pandas as pd
exam = pd.read_csv('exam.csv')
print(exam)

# query()를 사용해서 행 제한
print(exam.query('nclass == 1'))

print(exam.query('math > 50')) # 조건문 사용

# 1반 이면서 수학 점수가 50점 이상인 경우 출력 (AND)
print(exam.query('nclass == 1 & math >= 50'))

# 수학 점수가 90점 이상 이거나 영어 점수가 90점 이상인 경우 (OR)
print(exam.query('math >= 90 | english >= 90'))

# 필요한 변수만 추출하기 (열 제한)
print(exam['math'])

# 여러 열 출력
print(exam[['nclass', 'math', 'english']])

# 열 제거 하기
print(exam.drop(columns = 'math'))

# 행제한(query()) 과 열 제한([]) 조합하기
print(exam.query('nclass')[['english', 'math']])

# 일부만 출력하기 : 수학 점수가 50점 이상인 행만 추출한 다음, id, math 앞부분 5행까지 추출
print(exam.query('math>= 50')[['id', 'math']].head(8))

# 연습 문제 : 11개의 컬럼중에 category(자동차 종류), cty(도시연비) 추출해서 새로운 데이터 생성
mpg = pd.read_csv('mpg.csv')
new_mpg = mpg[['category', 'cty']]
print(new_mpg)

# 연습 문제 2: category(자동차 종류)가 'suv'인 자동차와 'compact'인 자동차 중 어떤 자동차의 ctv(도시 연비) 평균이 더 높은 것을 출력
suv = mpg.query('category == "suv"')['cty'].mean()
compact = mpg.query('category == "compact"')['cty'].mean()
if suv > compact:
    print(f"SUV : {suv}")
else:
    print(f"COMPACT : {compact}")

# 정렬하기
print(exam.sort_values('math', ascending= False)) # 수학 성적 기준 오름 차순 정렬

# 여러 정렬 기준 적용하기
print(exam.sort_values(['nclass', 'math'], ascending= [True, False]))

# 파생변수 추가 하기 (원본 데이터를 변형하지 않음)
exam.assign(total = exam['math'] + exam['english'] + exam['science'])
# print(exam)

# 파생 변수 추가 (원본 데이터 변형)
exam['total'] = exam['math'] + exam['english'] + exam['science']
print(exam)

print(exam.assign(test = np.where(exam['science'] >= 60, 'pass', 'fail')))

# 체이닝 메서드 활용
print(exam.assign(total = exam['math'] + exam['english'] + exam['science']).sort_values('total', ascending=False))

# mpg 데이터를 이용해 분석 문제를 해결해 보세요.
# mpg 데이터는 연비를 나타내는 변수가 hwy(고속도로 연비), cty(도시 연비) 두 종류로 분리되어 있습니다.
#두 변수를 각각 활용하는 대신 하나의 합산 연비 변수를 만들어 분석하려고 합니다.

# 1. mpg 데이터 복사본을 만들고, cty와 hwy를 더한 ‘합산 연비 변수’를 추가하세요.
mpg = pd.read_csv('mpg.csv')
mpg = mpg.copy()
print(mpg)
mpg['total'] = mpg['cty'] + mpg['hwy']  # '합산 연비' 변수 추가
print(mpg)
# 2. 앞에서 만든 ‘합산 연비 변수’를 2로 나눠 ‘평균 연비 변수’를 추가하세요.
mpg['mean_total'] = mpg['total'] / 2
print(mpg)

# 3. ‘평균 연비 변수’가 가장 높은 자동차 3종의 데이터를 출력하세요.
print(mpg.sort_values('mean_total', ascending=False).head(3))

# 4. 1~3번 문제를 해결할 수 있는 하나로 연결된 pandas 구문을 만들어 실행해 보세요. 데이터는 복사본 대신 mpg 원본을 이용하세요.
print(mpg.assign(total = lambda x: x['cty'] + x['hwy'], mean = lambda x: x['total']/2)\
    .sort_values('mean_total', ascending=False).head(3))
