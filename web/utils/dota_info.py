import requests

"""id ОБЯЗАТЕЛЬНО должен быть steam32"""


def matches(id):
    response = requests.get(f"https://api.opendota.com/api/players/{id}/matches")
    return response.json()