from flask import jsonify
import csv

def gender_data(region):
    # CSV 파일 읽기
    with open('./gender.csv', encoding='cp949') as f:
        reader = csv.reader(f)
        m = []
        f = []
        for row in reader:
            if region in row[0]:
                m = [-int(i) for i in row[3:104]] # 남성 인구수 데이터 추출, 음수로 반환은 챠트 그리기 위해
                f = [int(i) for i in row[106:]] # 여성 인구수 데이터 추출

    # JSON 형태로 데이터 변환
    data = {'male': m, 'female': f}
    return jsonify(data)