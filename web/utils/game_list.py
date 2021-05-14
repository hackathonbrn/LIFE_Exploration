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

print(game_csgo("36F3CB6FC3BF7C1FEBACC6A26C956F4B", "76561198225947128"))
print(game_dota2("36F3CB6FC3BF7C1FEBACC6A26C956F4B", "76561198225947128"))