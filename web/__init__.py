import os

from fastapi import FastAPI
from fastapi.staticfiles import StaticFiles
from fastapi.middleware.cors import CORSMiddleware
from apps.test.views import router as test_router
from apps.user.views import router as user_router
from apps.games.views import router as games_router
from apps.steam_auth.views import router as sa_router
from apps.requests.views import router as requests_router


app = FastAPI(docs_url='/', title='Hackathon API')

app.secret_key = os.urandom(15).hex()
app.mount("/static", StaticFiles(directory="static"), name="static")
app.add_middleware(
    CORSMiddleware,
    allow_origins=['*'],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
# app.include_router(test_router, tags=['Тестовые роуты'], prefix='/test')
app.include_router(user_router, tags=['Пользователь'], prefix='/user')
app.include_router(games_router, tags=['Игры'], prefix='/games')
app.include_router(sa_router, tags=['Steam Аутентификация'])
app.include_router(requests_router, tags=['Заявки на тренировку'], prefix='/request')
