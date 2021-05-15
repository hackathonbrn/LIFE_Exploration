import requests as r
from config import STEAM_WEB_API_KEY as key


def game_csgo(id):
    response = r.get(
        f"http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key={key}&steamid={id}&format=json")
    response = response.json()
    games = response["response"]["games"]
    a = [i for i in games if i.get("appid") == 730]
    return a


def game_dota2(id):
    response = r.get(
        f"http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key={key}&steamid={id}&format=json")
    response = response.json()
    games = response["response"]["games"]
    a = [i for i in games if i.get("appid") == 570]
    return a

#print(game_csgo("76561198225947128"))
#print(game_dota2("76561198225947128"))