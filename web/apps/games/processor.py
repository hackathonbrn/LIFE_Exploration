# Тут лежат функции, возвращающие в роут уже подготовленные данные(функции из processor.py)
from apps.games.provider import Provider
from utils.csgo import get_csgo_playtime
from utils.dota2 import get_dota2_playtime, get_dota2_rank


class Processor:
    def __init__(self):
        self.provider = Provider()

    def get_games(self) -> list:
        return self.provider.get_games()

    @staticmethod
    def get_ingame_data(game_name: str, steam_id: int):
        if game_name == 'csgo':
            return get_csgo_playtime(steam_id)
        elif game_name == 'dota2':
            dota2_playtime = get_dota2_playtime(steam_id)
            dota2_rank = get_dota2_rank(steam_id)
            response = {**dota2_playtime, **dota2_rank}
            return response
        else:
            return {}
