package com.dtobuilder.sender;

import lombok.RequiredArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RequiredArgsConstructor
public class Sender {

    private final AllRequestParameters rp;

    private RequestBuilder requestBuilder = new RequestBuilder();

    public String send() throws Exception {

        URL url = new URL(rp.baseUrl + requestBuilder.getParams(rp));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        requestBuilder.setMethod(connection, rp);
        requestBuilder.setAuthorization(connection, rp);
        requestBuilder.setHeaders(connection, rp);
        requestBuilder.sendBodyIfExists(connection, rp);
        return requestBuilder.getResponse(connection);
    }
}
