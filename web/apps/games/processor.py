# Тут лежат функции, возвращающие в роут уже подготовленные данные(функции из processor.py)
from apps.games.provider import Provider


class Processor:
    def __init__(self):
        self.provider = Provider()

    def get_games(self) -> list:
        return self.provider.get_games()
