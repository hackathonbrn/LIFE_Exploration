from fastapi import Body, Request, Query, APIRouter, Response
from .processor import Processor

router = APIRouter()


@router.get("/get_chat")
def get_chat(id_first: int, id_second: int) -> list:
    return Processor().get_chat(id_first, id_second)


@router.get("/add_message")
def add_message(id_frist: int, id_second: int, message: str) -> Response:
    Processor().add_message(id_frist, id_second, message)
    return Response(status_code=200)
