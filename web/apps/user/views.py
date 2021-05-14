from fastapi import Body, Request, Query, APIRouter

router = APIRouter()


@router.get("/")
def read_root():
    return {"Hello": "World"}
