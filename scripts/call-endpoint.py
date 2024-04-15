import requests
import time

def call_endpoint(url):
    try:
        headers = {
            "Authorization": "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJSLW1ycEV4QUpkRzBUdGlxempQcnVDUU92MnVwQ01lWmhJTVBpcnZUdm1ZIn0.eyJleHAiOjE3MTMxOTY2NjMsImlhdCI6MTcxMzE5MzA2MywianRpIjoiMzIwZDc1ZWUtZjMwYS00YTU0LWEzNjMtMDk2N2RhZDg2MTBiIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTAwL2F1dGgvcmVhbG1zL21vamhvbWUiLCJzdWIiOiIyMzI4YmU5YS1kNjEyLTQzZWUtOWQ4MC00MTllMDYyYjczNGYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJtb2pob21lLWJhY2tlbmQiLCJzZXNzaW9uX3N0YXRlIjoiNjdkN2RiMzgtMjAyYi00NDZiLWFmMGYtNWNiZjc4YWNjZmI2IiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJ1c2VyIiwiZGVmYXVsdC1yb2xlcy1tb2pob21lIl19LCJzY29wZSI6InByb2ZpbGUgbWljcm9wcm9maWxlLWp3dCBlbWFpbCIsInNpZCI6IjY3ZDdkYjM4LTIwMmItNDQ2Yi1hZjBmLTVjYmY3OGFjY2ZiNiIsInVwbiI6InRlc3R1c2VyIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImdyb3VwcyI6WyJ1c2VyIiwiZGVmYXVsdC1yb2xlcy1tb2pob21lIl0sInByZWZlcnJlZF91c2VybmFtZSI6InRlc3R1c2VyIiwiZ2l2ZW5fbmFtZSI6IiIsImZhbWlseV9uYW1lIjoiIn0.Wjc0Q_4o8r-67sHi4FQi-E4XLa8k-JalbS_z83Te9SIcBAWQEaNCfw1N_i5r-p-T5mlS6B9mLyue09CtLK3SPZoGMP7qDo09qforQZP_Aaga9gSMEu8b44WKRW08FjGUq8KSn4bcqM9wvsXfbm-Ji-wRQKK0oxPQ9H23Ll8gdYrEi8QIP3ND3S7OrqDmcvPCunmQaXmFzBUprERVeJHosCfRS89HiFpYn8hKagr1ggF6cpx_lcvaMS2l4Xs7qUKfL8wxm60UIMik1TG-ZG9zRgwUUDb_ef0hbIv7HCHBD5fD8p_HonUVKw2f4qt0KKNGemknY_1CdWYrm73J2l53Ng"
        }
        requests.get(url, headers=headers)
    except requests.RequestException as e:
        print("Error:", e)

if __name__ == "__main__":
    endpoint_url = "http://localhost:8200/api/secured/finances"

    while True:
        call_endpoint(endpoint_url)
        time.sleep(0.1)