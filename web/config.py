import os

BASE_DIR = os.path.dirname(__file__)
SQL_ROOT_PATH = os.path.join(BASE_DIR, 'sql')
HOST = 'http://127.0.0.1:8000'
STEAM_WEB_API_KEY = os.environ["STEAM_WEB_API_KEY"]

DATABASE = {
    "dbname": os.environ["POSTGRES_DB"],
    "user": os.environ["POSTGRES_USER"],
    "host": os.environ["POSTGRES_HOST"],
    "password": os.environ["POSTGRES_PASSWORD"]
}

