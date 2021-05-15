from fastapi import Body, Request, Query, APIRouter, Response
from .processor import Processor
from .schemas import Games

router = APIRouter()


@router.get("/get_games", response_model=Games)
def get_games():
    return {'games': Processor().get_games()}

