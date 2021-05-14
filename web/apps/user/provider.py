# Тут лежат функции, поставляющие какие-то данные. Допустим, запрос на получение юзера из БД
import os
from base.provider import BaseProvider


class Provider(BaseProvider):
    def __init__(self):
        super().__init__('user')

    def get_user_id(self, steam_id: str) -> int:
        user_id = self.exec_by_file('get_user_id.tmpl', {'steam_id': steam_id})
        try:
            return user_id[0].get('id')
        except:
            return None

    def get_user(self, user_id: int) -> dict:
        return self.exec_by_file('get_user.tmpl', {'id': user_id})[0]
