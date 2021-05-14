import requests


def person_info(key, id):
    response = requests.get(f"http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key={key}&steamids={id}")
    response = response.json()
    ls = response["response"]["players"]
    return ls

print(person_info("36F3CB6FC3BF7C1FEBACC6A26C956F4B", "76561198225947128"))