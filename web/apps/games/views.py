from fastapi import Body, Request, Query, APIRouter, Response
from .processor import Processor
from .schemas import Games
from utils.game_list import get_csgo_playtime, get_dota2_playtime

router = APIRouter()


@router.get("/get_games", response_model=Games)
def get_games():
    return {'games': Processor().get_games()}


@router.get('/get_playtime')
def get_playtime(game_name: str, user_id: int):
    if game_name == 'csgo':
        return get_csgo_playtime(user_id)
    elif game_name == 'dota2':
        return get_dota2_playtime(user_id)
    else:
        return Response(status_code=400)
