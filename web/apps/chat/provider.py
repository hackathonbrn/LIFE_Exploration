# Тут лежат функции, поставляющие какие-то данные. Допустим, запрос на получение юзера из БД
import os
from typing import Optional
from base.provider import BaseProvider


class Provider(BaseProvider):
    def __init__(self):
        super().__init__('chat')

    def get_chat(self, id_first: int, id_second: int) -> list:
        return self.exec_by_file('get_chat.tmpl', {'id_first': id_first, 'id_second': id_second})

    def add_message(self, id_first: int, id_second: int, message:str) -> None:
        self.exec_by_file('add_message.tmpl', {'id_first': id_first, 'id_second': id_second, 'message': message})
