# Тут лежат функции, возвращающие в роут уже подготовленные данные(функции из processor.py)
from typing import Optional
from .provider import Provider


class Processor:
    def __init__(self):
        self.provider = Provider()

    def get_chat(self, id_first: int, id_second: int) -> list:
        return self.provider.get_chat(id_first, id_second)

    def add_message(self, id_first: int, id_second: int, message: str) -> None:
        self.provider.add_message(id_first, id_second, message)
