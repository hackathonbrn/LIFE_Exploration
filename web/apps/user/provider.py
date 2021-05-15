# Тут лежат функции, поставляющие какие-то данные. Допустим, запрос на получение юзера из БД
import os
from typing import Optional
from base.provider import BaseProvider
from .schemas import User, Coach


class Provider(BaseProvider):
    def __init__(self):
        super().__init__('user')

    def get_user_id(self, steam_id: str) -> Optional[int]:
        user_id = self.exec_by_file('get_user_id.tmpl', {'steam_id': steam_id})
        return user_id[0].get('id') if user_id else None

    def get_user(self, user_id: int) -> dict:
        user_info = self.exec_by_file('get_user.tmpl', {'id': user_id})[0]
        user_info['le_pararms'] = {}
        if user_info.get('reviews_learner'):
            rating = []
            sociability = []
            adequacy = []
            qualification = []
            for record in user_info.get('reviews_learner'):
                rating.append(record.get('rating'))
                sociability.append(record.get('sociability'))
                adequacy.append(record.get('adequacy'))
                qualification.append(record.get('qualification'))
            user_info['le_pararms']['rating'] = sum(rating) / len(rating) if rating else None
            user_info['le_pararms']['sociability'] = sum(sociability) / len(sociability) if sociability else None
            user_info['le_pararms']['adequacy'] = sum(adequacy) / len(adequacy) if adequacy else None
            user_info['le_pararms']['qualification'] = sum(qualification) / len(qualification) if qualification else None
        else:
            user_info['le_pararms']['rating'] = None
            user_info['le_pararms']['sociability'] = None
            user_info['le_pararms']['adequacy'] = None
            user_info['le_pararms']['qualification'] = None
        user_info['co_pararms'] = {}
        if user_info.get('reviews_coach'):
            rating = []
            sociability = []
            coach_level = []
            qualification = []
            for record in user_info.get('reviews_coach'):
                rating.append(record.get('rating'))
                sociability.append(record.get('sociability'))
                coach_level.append(record.get('coach_level'))
                qualification.append(record.get('qualification'))
            user_info['co_pararms']['rating'] = sum(rating)/len(rating) if rating else None
            user_info['co_pararms']['sociability'] = sum(sociability) / len(sociability) if sociability else None
            user_info['co_pararms']['coach_level'] = sum(coach_level) / len(coach_level) if coach_level else None
            user_info['co_pararms']['qualification'] = sum(qualification) / len(qualification) if qualification else None
        else:
            user_info['co_pararms']['rating'] = None
            user_info['co_pararms']['sociability'] = None
            user_info['co_pararms']['coach_level'] = None
            user_info['co_pararms']['qualification'] = None
        return user_info

    def add_user(self, user_dict: dict) -> int:
        return self.exec_by_file('add_user.tmpl', user_dict)[0].get('id')

    def change_user(self, user_dict: User) -> dict:
        self.exec_by_file('change_user.tmpl', user_dict.dict())
        if user_dict.dict().get('games_user') is not None:
            for game in user_dict.dict().get('games_user'):
                self.exec_by_file('user_games.tmpl', game)
        if user_dict.dict().get('reviews_learner') is not None:
            for review in user_dict.dict().get('reviews_learner'):
                self.exec_by_file('reviews_learner.tmpl', review)
        if user_dict.dict().get('reviews_coach') is not None:
            for review in user_dict.dict().get('reviews_coach'):
                self.exec_by_file('reviews_coach.tmpl', review)
        if user_dict.dict().get('learners_learner') is not None:
            for review in user_dict.dict().get('learners_learner'):
                self.exec_by_file('learners.tmpl', review)
        if user_dict.dict().get('learners_coach') is not None:
            for review in user_dict.dict().get('learners_coach'):
                self.exec_by_file('learners.tmpl', review)
        return user_dict.dict()

    def get_coach(self, coach_dict: Coach) -> list:
        coach = self.exec_by_file('get_coach.tmpl', coach_dict.dict())
        if coach_dict.dict().get('id_game') is not None:
            id_game = coach_dict.dict().get('id_game')
            coach = list(filter(lambda x: x.get('id_game') == id_game, coach))
        coach = sorted(coach, key=lambda x: x.get('rating'), reverse=True)
        return coach
