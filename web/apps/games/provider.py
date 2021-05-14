# Тут лежат функции, поставляющие какие-то данные. Допустим, запрос на получение юзера из БД
import os
from base.provider import BaseProvider


class Provider(BaseProvider):
    def __init__(self):
        super().__init__('games')

    def get_games(self) -> list:
        return self.exec_by_file('get_games.tmpl', {})
