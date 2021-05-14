# Тут лежат функции, возвращающие в роут уже подготовленные данные(функции из processor.py)
from apps.user.provider import Provider


class Processor:
    def __init__(self):
        self.provider = Provider()

    def cabinet(self, steam_id: str) -> dict:
        user_id = self.provider.get_user_id(steam_id)
        if user_id:
            return self.provider.get_user(user_id)
        return None

    def get_user(self, user_id: int) -> dict:
        return self.provider.get_user(user_id)
