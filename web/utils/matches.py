import requests

"""id ОБЯЗАТЕЛЬНО должен быть steam32"""


def matches(id):
    id = id - 76561197960265728
    response = requests.get(f"https://api.opendota.com/api/players/{id}/matches")
    return response.json()