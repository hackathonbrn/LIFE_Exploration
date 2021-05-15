import requests as r
from config import STEAM_WEB_API_KEY as key


def person_info(id):
    response = r.get(f"http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key={key}&steamids={id}")
    response = response.json()
    ls = response["response"]["players"]
    return ls

#print(person_info("76561198225947128"))
