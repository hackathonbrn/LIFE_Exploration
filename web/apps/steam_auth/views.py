import os

from fastapi import APIRouter

from apps.steam_auth.steamsignin import SteamSignIn
from fastapi import Depends, Request, Response
from config import HOST
from apps.steam_auth.processor import Processor

router = APIRouter()


@router.get('/auth/login')
async def login(token: str, steam_signin: SteamSignIn = Depends(SteamSignIn)):
    url = steam_signin.construct_url(HOST + f'/auth/process?token={token}')
    return steam_signin.redirect_user(url)


@router.get('/auth/process')
async def process_login(token: str, request: Request, steam_signin: SteamSignIn = Depends(SteamSignIn)):
    if not token:
        return Response(status_code=403)

    steam64id = steam_signin.validate_results(request.query_params)
    if not steam64id:
        return Response(status_code=404)

    proc = Processor()
    if proc.is_token_exists(token):
        return Response(status_code=406)

    accepted = proc.add_session(token, steam64id)
    if not accepted:
        return Response(status_code=500)

    return Response(status_code=202, headers={'session': steam64id})


@router.get('/auth/get_steam_id')
async def get_steam_id(token: str):
    if not token:
        return Response(status_code=403)

    proc = Processor()
    return proc.get_steam_id(token)