# Тут лежат функции, поставляющие какие-то данные. Допустим, запрос на получение юзера из БД
import os
from base.provider import BaseProvider


class Provider(BaseProvider):
    def __init__(self):
        super().__init__('user')

    def get_user(self, steam_id: str) -> dict:
        return self.exec_by_file('get_user.tmpl', {'steam_id': steam_id})[0]

