from flask import Flask, jsonify
from flask_cors import CORS  # CORS 문제 해결

from 시각화test import gender_data  # 시긱화test 모듈에서 gender_data 가져오기

app = Flask(__name__)
CORS(app)  # React와의 CORS 문제 해결

app.add_url_rule('/api/gender/<region>', 'gender_data', gender_data, methods=['GET'])

def get_gender_data(region):
    data = gender_data(region)  # 해당 지역의 gender_data 가져오기
    return jsonify(data)

if __name__ == '__main__':
    app.run(debug=True)
