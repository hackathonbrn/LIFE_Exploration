import requests
from config import STEAM_WEB_API_KEY


def get_csgo_playtime(id):
    response = requests.get(
        f"http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?"
        f"key={STEAM_WEB_API_KEY}"
        f"&steamid={id}"
        f"&format=json")
    response = response.json()
    games = response["response"]["games"]

    for i in games:
        appid = i.get('appid')
        if appid != 730:
            continue

        new_time = i.get('playtime_forever') // 60
        data = {'appid': appid, 'played_time': new_time}
        return data




def get_dota2_playtime(id):
    response = requests.get(
        f"http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?"
        f"key={STEAM_WEB_API_KEY}"
        f"&steamid={id}"
        f"&format=json")
    response = response.json()
    games = response["response"]["games"]

    for i in games:
        appid = i.get('appid')
        if appid != 570:
            continue

        new_time = i.get('playtime_forever') // 60
        data = {'appid': appid, 'played_time': new_time}
        return data

