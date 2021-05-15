import requests as r
from config import STEAM_WEB_API_KEY


def person_info(steam_id):
    response = r.get(f"http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?"
                     f"key={STEAM_WEB_API_KEY}"
                     f"&steamids={steam_id}")
    response = response.json()
    ls = response["response"]["players"][0]
    return ls

