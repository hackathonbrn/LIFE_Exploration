import os

BASE_DIR = os.path.dirname(__file__)
SQL_ROOT_PATH = os.path.join(BASE_DIR, 'sql')

DATABASE = {
    "dbname": os.environ["DB_NAME"],
    "user": os.environ["DB_LOGIN"],
    "host": os.environ["DB_HOST"],
    "password": os.environ["DB_PASSWORD"]
}

