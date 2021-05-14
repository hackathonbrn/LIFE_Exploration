from fastapi import APIRouter

from apps.steam_auth.steamsignin import SteamSignIn
from fastapi import Depends, Request, Response
from config import HOST

router = APIRouter()


@router.get('/auth/login')
async def login(steam_signin: SteamSignIn = Depends(SteamSignIn)):
    url = steam_signin.construct_url(HOST + '/auth/process')
    return steam_signin.redirect_user(url)


@router.get('/auth/process')
async def process_login(request: Request, steam_signin: SteamSignIn = Depends(SteamSignIn)):
    steam64id = steam_signin.validate_results(request.query_params)
    return Response(status_code=202, headers={'session': steam64id})
