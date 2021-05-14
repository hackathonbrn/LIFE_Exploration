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
    games_user: List[Dict[str, Union[int, str]]]
    reviews_learner: List[Dict[str, Union[int, str]]]
    reviews_coach: List[Dict[str, Union[int, str]]]
    learners_learner: List[Dict[str, Union[int, str]]]
    learners_coach: List[Dict[str, Union[int, str]]]

