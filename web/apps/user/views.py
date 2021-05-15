from fastapi import Body, Request, Query, APIRouter, Response
from .processor import Processor
from .schemas import User

router = APIRouter()


@router.get("/cabinet", response_model=User)
def cabinet(steam_id: str = None):
    user_info = None
    if steam_id:
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


@router.post("/change_user", response_model=User)
def change_user(user_dict: User = Body(..., description='Свойства предмета'),
                old_user_dict: User = Body(..., description='Старые свойства предмета')):
    user_info = None
    if user_dict and old_user_dict:
        user_info = Processor().change_user(user_dict, old_user_dict)
    if user_info:
        return user_info
    return Response(status_code=404)
