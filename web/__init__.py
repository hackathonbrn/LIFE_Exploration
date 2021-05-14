from fastapi import FastAPI
from fastapi.staticfiles import StaticFiles
from fastapi.middleware.cors import CORSMiddleware
from apps.test.views import router as test_router
from apps.user.views import router as user_router

app = FastAPI(docs_url='/', title='Hackathon API')

app.mount("/static", StaticFiles(directory="static"), name="static")
app.add_middleware(
    CORSMiddleware,
    allow_origins=['*'],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
app.include_router(test_router, tags=['Test Routes'], prefix='/test')
app.include_router(user_router, tags=['User'], prefix='/user')
