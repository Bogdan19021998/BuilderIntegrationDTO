package com.dtobuilder.sender.authorization;

import com.dtobuilder.sender.AllRequestParameters;
import com.dtobuilder.sender.Header;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class AuthenticationHolder {

    private final AllRequestParameters rp;

    // todo add in params

    public Header authorizationOnHeaderBasicEncoded(String name, String password) {
        Map<AuthorizationValues, String> parameters = new HashMap<>();
        parameters.put(AuthorizationValues.NAME, name);
        parameters.put(AuthorizationValues.PASSWORD, password);
        rp.authenticationParameters = new AuthenticationParameters(AuthorizationType.BASIC, parameters);
        return new Header(rp);
    }

    public Header authorizationOnBody(String filed, String apiKey) {
        Map<AuthorizationValues, String> parameters = new HashMap<>();
        parameters.put(AuthorizationValues.NAME_FIELD_API_KEY, filed);
        parameters.put(AuthorizationValues.API_KEY, apiKey);
        rp.authenticationParameters = new AuthenticationParameters(AuthorizationType.BODY, parameters);
        return new Header(rp);
    }

    public Header authorizationEmpty() {
        rp.authenticationParameters = new AuthenticationParameters(AuthorizationType.EMPTY, new HashMap<>());
        return new Header(rp);
    }
}
