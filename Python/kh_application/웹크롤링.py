from bs4 import BeautifulSoup

# from JSON과SpringBoot import response

html = '''
<html>
    <table border=1> 
        <tr>
            <td> 항목 </td> 
            <td> 2013 </td> 
            <td> 2014 </td> 
            <td> 2015 </td>
        </tr> 
        <tr>
            <td> 매출액 </td> 
            <td> 100 </td> 
            <td> 200 </td>
            <td> 300 </td>
        </tr> 
    </table>
    <ul>
        <li> 100 </li>
        <li> 200 </li>
    </ul>
    <ol>
        <li> 300 </li>
        <li> 400 </li>
    </ol>
</html> 
'''
# list는 시퀀스형 data이기 때문에 향상된 for문 사용 가능 <-- 매우중요
soup = BeautifulSoup(html, 'html.parser')
# print(soup.select('td'))
rst = soup.select('td')
for e in rst:
    print(e.text, end=" ")

print()

rst2 = soup.select('ul li')
for e in rst2:
    print(e.text, end=" ")

# 특정 요소 찾기(find 와 find_all )
# find : 조건에 맞는 첫 번째 요소를 반환
# find_all : 조건에 맞는 모든 요소를 리스트로 반환

import requests
response = requests.get("https://naver.com")
soup = BeautifulSoup(response.text, 'lxml')
# print(soup)

print(soup.find(attrs={"class": "notify_area"}))