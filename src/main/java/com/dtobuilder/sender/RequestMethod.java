package com.dtobuilder.sender;

import com.dtobuilder.sender.authorization.AuthenticationHolder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestMethod {

    private final AllRequestParameters rp;

    public AuthenticationHolder GET() {
        rp.requestMethod = "GET";
        return new AuthenticationHolder(rp);
    }

    public AuthenticationHolder HEAD() {
        rp.requestMethod = "HEAD";
        return new AuthenticationHolder(rp);
    }

    public AuthenticationHolder POST() {
        rp.requestMethod = "POST";
        return new AuthenticationHolder(rp);
    }

    public AuthenticationHolder PUT() {
        rp.requestMethod = "PUT";
        return new AuthenticationHolder(rp);
    }

    public AuthenticationHolder DELETE() {
        rp.requestMethod = "DELETE";
        return new AuthenticationHolder(rp);
    }

    public AuthenticationHolder CONNECT() {
        rp.requestMethod = "CONNECT";
        return new AuthenticationHolder(rp);
    }

    public AuthenticationHolder OPTIONS() {
        rp.requestMethod = "OPTIONS";
        return new AuthenticationHolder(rp);
    }

    public AuthenticationHolder TRACE() {
        rp.requestMethod = "TRACE";
        return new AuthenticationHolder(rp);
    }

    public AuthenticationHolder PATCH() {
        rp.requestMethod = "PATCH";
        return new AuthenticationHolder(rp);
    }
}
