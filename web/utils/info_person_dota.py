import requests

"""id ОБЯЗАТЕЛЬНО должен быть steam32"""


def info_person_dota(id):
    response = requests.get(f"""https://api.opendota.com/api/players/{id}""")
    return response.json()