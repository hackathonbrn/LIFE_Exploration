from fastapi import Body, Request, Query, APIRouter, Response
from .processor import Processor
from .schemas import User, Coach, Reviews

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
def change_user(user_dict: User = Body(..., description='Свойства предмета')):
    user_info = None
    if user_dict:
        user_info = Processor().change_user(user_dict)
    if user_info:
        return user_info
    return Response(status_code=404)


@router.post("/get_coach")
def get_coach(coach_dict: Coach = Body(..., description='Свойства предмета')):
    return Processor().get_coach(coach_dict)


@router.post("/set_reviews")
def set_reviews(reviews_dict: Reviews = Body(..., description='Данные отзывов')):
    return Processor().set_reviews(reviews_dict)
