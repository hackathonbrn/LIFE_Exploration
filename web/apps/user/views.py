from fastapi import Body, Request, Query, APIRouter
from .processor import Processor

router = APIRouter()


@router.get("/get_user")
def get_user(request: Request):
    steam_id = request.headers.get('session')
    return Processor().get_user(steam_id)
