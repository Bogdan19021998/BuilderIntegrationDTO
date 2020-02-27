package com.dtobuilder.sender.authorization;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class AuthenticationParameters {

    private final AuthorizationType type;
    private final Map<AuthorizationValues, String> values;
}
