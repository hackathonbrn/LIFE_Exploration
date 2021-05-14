from fastapi import Body, Request, Query, APIRouter, Response
from .processor import Processor
from .schemas import User

router = APIRouter()


@router.get("/cabinet", response_model=User)
def cabinet(request: Request):
    steam_id = request.headers.get('session')
    user_info = Processor().cabinet(steam_id)
    if user_info:
        return user_info
    return Response(status_code=404)


@router.get("/get_user", response_model=User)
def get_user(user_id: int = None):
    user_info = None
    if user_id:
        user_info = Processor().get_user(user_id)
    if user_info:
        return user_info
    return Response(status_code=404)
