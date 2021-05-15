import requests

"""id ОБЯЗАТЕЛЬНО должен быть steam32"""


def info_person_dota(id):
    id = id - 76561197960265728
    response = requests.get(f"""https://api.opendota.com/api/players/{id}""")
    return response.json()