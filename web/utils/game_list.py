import requests


def game_list(key, id):
    response = requests.get(f"http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key={key}&steamid={id}&format=json")
    response = response.json()
    games = response["response"]["games"]
    a = [i for i in games if i.get("appid") in [730, 570]]
    return a


print(game_list("36F3CB6FC3BF7C1FEBACC6A26C956F4B", "76561198225947128"))