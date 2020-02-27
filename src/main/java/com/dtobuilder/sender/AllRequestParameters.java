package com.dtobuilder.sender;

import com.dtobuilder.sender.authorization.AuthenticationParameters;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class AllRequestParameters {

    public String baseUrl;
    public String requestMethod;
    public AuthenticationParameters authenticationParameters;
    public Map<String, String> headers = new HashMap<>();
    public Map<String, String> params = new HashMap<>();
    public Object body;
}
