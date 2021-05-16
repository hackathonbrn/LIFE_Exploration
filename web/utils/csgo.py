import requests
from config import STEAM_WEB_API_KEY

# 76561198129263830
# 76561198094822422
# 76561198225947128

def get_csgo_playtime(steam_id):
    response = requests.get(
        f"http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?"
        f"key={STEAM_WEB_API_KEY}"
        f"&steamid={steam_id}"
        f"&format=json")
    response = response.json()
    games = response["response"].get('games')

    if not games:
        return {'id_game': 1, 'played_time': 0}

    data = None
    for i in games:
        appid = i.get('appid')
        if appid != 730:
            continue

        new_time = i.get('playtime_forever') // 60
        data = {'id_game': 1, 'played_time': new_time}
    return data or {}

