from apps.steam_auth.provider import Provider


class Processor:
    def __init__(self):
        self.provider = Provider()

    def add_session(self, token, steam_id):
        return self.provider.add_session(token, steam_id)

    def get_steam_id(self, token):
        return self.provider.get_steam_id(token)

