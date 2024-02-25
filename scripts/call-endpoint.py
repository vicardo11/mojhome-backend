import requests
import time

def call_endpoint(url):
    try:
        requests.get(url)
    except requests.RequestException as e:
        print("Error:", e)

if __name__ == "__main__":
    endpoint_url = "http://localhost:8200/finances"

    while True:
        call_endpoint(endpoint_url)
        time.sleep(0.5)