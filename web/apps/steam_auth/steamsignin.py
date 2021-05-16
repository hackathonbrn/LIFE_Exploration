import re, logging
import urllib
import urllib.request
from urllib.parse import urlencode
from starlette.responses import RedirectResponse
from starlette.status import HTTP_303_SEE_OTHER

logging.getLogger(name=__name__)


class SteamSignIn:
    _provider = 'https://steamcommunity.com/openid/login'

    def redirect_user(self, post_data):
        return RedirectResponse(url="{0}?{1}".format(self._provider, post_data),
                                status_code=HTTP_303_SEE_OTHER,
                                headers={'Content-Type': 'application/x-www-form-urlencoded'})

    def construct_url(self, response_url):
        refined_scripts = re.search('(?:http)', response_url)
        if refined_scripts is None or refined_scripts.group(0) is None:
            response_url = "http://{0}".format(response_url)

        AUTH_PARAMETERS = {
            "openid.ns": "http://specs.openid.net/auth/2.0",
            "openid.mode": "checkid_setup",
            "openid.return_to": response_url,
            "openid.realm": response_url,
            "openid.identity": "http://specs.openid.net/auth/2.0/identifier_select",
            "openid.claimed_id": "http://specs.openid.net/auth/2.0/identifier_select"
        }

        return urlencode(AUTH_PARAMETERS)

    def validate_results(self, results):
        VALIDATION_ARGS = {
            'openid.assoc_handle': results['openid.assoc_handle'],
            'openid.signed': results['openid.signed'],
            'openid.sig': results['openid.sig'],
            'openid.ns': results['openid.ns']
        }

        # Basically, we split apart one of the args steam sends back only to send it back to them to validate!
        # We also append check_authentication which tells OpenID2 to actually yknow, validate what we send.
        signed_args = results['openid.signed'].split(',')

        for item in signed_args:
            item_arg = 'openid.{0}'.format(item)
            if results[item_arg] not in VALIDATION_ARGS:
                VALIDATION_ARGS[item_arg] = results[item_arg]

        VALIDATION_ARGS['openid.mode'] = 'check_authentication'
        parsed_arg = urlencode(VALIDATION_ARGS).encode("utf-8")

        with urllib.request.urlopen(self._provider, parsed_arg) as requestData:
            response_data = requestData.read().decode('utf-8')
        if not re.search('is_valid:true', response_data):
            return False

        steam64id = re.search('https://steamcommunity.com/openid/id/(\d+)', results['openid.claimed_id'])
        if steam64id is not None or steam64id.group(1) is not None:
            return steam64id.group(1)
        else:
            # If we somehow fail to get a valid steam64ID, just return false
            return False

