# Тут лежат функции, поставляющие какие-то данные. Допустим, запрос на получение юзера из БД
import os
from typing import Optional
from base.provider import BaseProvider
from .schemas import User


class Provider(BaseProvider):
    def __init__(self):
        super().__init__('user')

    def get_user_id(self, steam_id: str) -> Optional[int]:
        user_id = self.exec_by_file('get_user_id.tmpl', {'steam_id': steam_id})
        return user_id[0].get('id') if user_id else None

    def get_user(self, user_id: int) -> dict:
        return self.exec_by_file('get_user.tmpl', {'id': user_id})[0]

    def add_user(self, user_dict: dict) -> int:
        return self.exec_by_file('add_user.tmpl', user_dict)[0].get('id')

    def change_user(self, user_dict: User, old_user_dict: User) -> bool:
        self.exec_by_file('change_user.tmpl', user_dict)
        if isinstance(user_dict, dict) and isinstance(old_user_dict, dict):
            if old_user_dict.get('games_user') != user_dict.get('games_user'):
                pass
            if old_user_dict.get('reviews_learner') != user_dict.get('reviews_learner'):
                pass
            if old_user_dict.get('reviews_coach') != user_dict.get('reviews_coach'):
                pass
            if old_user_dict.get('learners_learner') != user_dict.get('learners_learner'):
                pass
            if old_user_dict.get('learners_coach') != user_dict.get('learners_coach'):
                pass
        return self.exec_by_file('change_user.tmpl', user_dict)