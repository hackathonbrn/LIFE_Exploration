# Тут лежат функции, возвращающие в роут уже подготовленные данные(функции из processor.py)
from apps.user.provider import Provider


class Processor:
    def __init__(self):
        self.provider = Provider()

    def get_user(self, steam_id: str) -> dict:
        return self.provider.get_user(steam_id)
