# Тут лежат функции, возвращающие в роут уже подготовленные данные(функции из processor.py)
from typing import Optional
from apps.user.provider import Provider
from .schemas import User, Coach, Reviews
from utils.person_info import person_info


class Processor:
    def __init__(self):
        self.provider = Provider()

    def cabinet(self, steam_id: str) -> Optional[dict]:
        user_id = self.provider.get_user_id(steam_id)
        if not user_id:
            person_json = person_info(steam_id)
            user_dict = {
                'steam_id': steam_id,
                'username': person_json.get('personaname'),
                'avatar_url': person_json.get('avatar')
            }
            user_id = self.provider.add_user(user_dict)
        return self.provider.get_user(user_id)

    def get_user(self, user_id: int) -> dict:
        return self.provider.get_user(user_id)

    def change_user(self, user_dict: User) -> dict:
        return self.provider.change_user(user_dict)

    def get_coach(self, coach_dict: Coach) -> list:
        return self.provider.get_coach(coach_dict)

    def set_reviews(self, reviews_dict: Reviews) -> None:
        self.provider.set_reviews(reviews_dict)
