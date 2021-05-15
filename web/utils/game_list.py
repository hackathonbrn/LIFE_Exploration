import requests


def game_csgo(key, id):
    response = requests.get(
        f"http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key={key}&steamid={id}&format=json")
    response = response.json()
    games = response["response"]["games"]
    a = [i for i in games if i.get("appid") == 730]
    return a


def game_dota2(key, id):
    response = requests.get(
        f"http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key={key}&steamid={id}&format=json")
    response = response.json()
    games = response["response"]["games"]
    a = [i for i in games if i.get("appid") == 570]
    return a
