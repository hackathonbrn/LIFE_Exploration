from base.provider import BaseProvider


class Provider(BaseProvider):
    def __init__(self):
        super().__init__('steam_auth')

    def add_session(self, token, steam_id):
        return self.exec_by_file('add_session.tmpl', {'token': token, 'steam_id': steam_id})

    def get_steam_id(self, token):
        steam_id = self.exec_by_file('get_steam_id.tmpl', {'token': token})
        return steam_id[0] if any(steam_id) else steam_id

    def is_token_exists(self, token):
        return bool(self.exec_by_file('is_token_exists.tmpl', {'token': token})[0].get('count'))


