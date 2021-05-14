from fastapi import Body, Request, Query, APIRouter
from apps.test.schemas import Item

router = APIRouter()


@router.get("/")
def read_root():
    """ Тут описание метода в сваггере """
    return {"Hello": "World"}


@router.put("/item", response_model=Item)
def create_item(item: Item = Body(..., description='Свойства предмета'),
                user_id: str = Query(..., description='ID пользователя')):
    """
    Пример сериализатора <b>json -> python object</b>.
    Мы принимаем json в 'item', в котором поля строго типизированы

    Функция Body даёт преднастраиват тело запроса в сваггере(дефолтное значение и описание).
    Под "дефолтным значением" понимается схема валидатора, которая подставляется в сваггер.

    Функция Query по аналогии с Body, но обрабатывает только GET параметр. Тут задаём описание параметра и говорим
    '...'(эллипсисом),
    что параметр обязателен

    Тайп хинты можно использовать любые. Валидатор будет проверять их. Допустим, можно юзать List[str], если ожидаем
     ['test', 'test', ...]

    response_model - это та схема, которую мы вернём. Её покажет в "Responses" в сваггере.
    """
    print(item.id, item.name)
    print(user_id)
    return item


@router.get('/test', deprecated=True)
def test():
    """ Пример, как сделать депрекейтед роут """
    return "test"


@router.api_route('/test2', methods=['GET', 'POST'])
def test2(request: Request, test: str = Query(None, deprecated=True)):
    """ Пример роута с несколькими обрабатываемыми методами и депрекейтед методом"""
    print(test)
    if request.method == 'GET':
        return 'GET Request'
    elif request.method == 'POST':
        return 'POST Request'
    else:
        return "test"
