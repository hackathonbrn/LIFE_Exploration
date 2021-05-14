# Тут лежат функции, поставляющие какие-то данные. Допустим, запрос на получение юзера из БД
import os
from base.provider import BaseProvider


class Provider(BaseProvider):
    def __init__(self):
        super().__init__('user')

    def get_user(self, steam_id: str) -> dict:
        user = self.exec_by_file('get_user.tmpl', {'steam_id': steam_id})[0]
        user['reviews_coach'] = self.exec_by_file('get_reviews_coach.tmpl', user)
        user['reviews_learner'] = self.exec_by_file('get_reviews_learner.tmpl', user)
        user['learners_coach'] = self.exec_by_file('get_learners_coach.tmpl', user)
        user['learners_learner'] = self.exec_by_file('get_learners_learner.tmpl', user)
        user['games_user'] = self.exec_by_file('get_games_user.tmpl', user)
        return user

