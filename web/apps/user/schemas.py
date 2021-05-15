from typing import Dict, List, Union
from pydantic import BaseModel


class User(BaseModel):
    id: int
    username: str
    money: str
    acc_status: bool
    verification: bool
    avatar_url: str
    steam_id: int
    games_user: Union[List[Dict[str, Union[int, str]]], None]
    reviews_learner: Union[List[Dict[str, Union[int, str]]], None]
    reviews_coach: Union[List[Dict[str, Union[int, str]]], None]
    learners_learner: Union[List[Dict[str, Union[int, str]]], None]
    learners_coach: Union[List[Dict[str, Union[int, str]]], None]
