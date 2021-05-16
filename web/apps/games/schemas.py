from typing import Dict, List, Union
from pydantic import BaseModel


class Games(BaseModel):
    games: List[Dict[str, Union[int, str]]]
