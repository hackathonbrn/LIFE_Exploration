import requests
from config import STEAM_WEB_API_KEY


def get_dota2_rank(steam_id):
    steam_id -= 76561197960265728
    response = requests.get(f"https://api.opendota.com/api/players/{steam_id}").json()
    return {'rank': response.get('solo_competitive_rank')}


def get_dota2_playtime(steam_id):
    response = requests.get(
        f"http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?"
        f"key={STEAM_WEB_API_KEY}"
        f"&steamid={steam_id}"
        f"&format=json")
    response = response.json()
    print(response)
    games = response["response"].get('games')

    if not games:
        return {'id_game': 2, 'played_time': 0}

    data = None
    for i in games:
        appid = i.get('appid')
        if appid != 570:
            continue

        new_time = i.get('playtime_forever') // 60
        data = {'id_game': 2, 'played_time': new_time}
    return data or {}


def get_dota2_matches(steam_id):
    steam_id -= 76561197960265728
    response = requests.get(f"https://api.opendota.com/api/players/{steam_id}/matches")
    return response.json()

