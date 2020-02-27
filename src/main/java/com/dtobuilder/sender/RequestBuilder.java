package com.dtobuilder.sender;

import com.dtobuilder.sender.authorization.AuthorizationValues;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class RequestBuilder {

    public String getParams(AllRequestParameters rp) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();

        if (!rp.params.isEmpty()) {
            sb.append("?");
            AtomicInteger counter = new AtomicInteger(rp.params.size());
            for (Map.Entry<String, String> entry : rp.params.entrySet()) {
                sb.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), java.nio.charset.StandardCharsets.UTF_8.name()));
                if (counter.decrementAndGet() != 0) {
                    sb.append('&');
                }
            }
            rp.baseUrl += sb.toString();
        }
        return sb.toString();
    }

    public void setAuthorization(HttpURLConnection connection, AllRequestParameters rp) throws JsonProcessingException {
        Map<AuthorizationValues, String> authorizationValues = rp.authenticationParameters.getValues();
        switch (rp.authenticationParameters.getType()) {
            case BASIC:
                connection.setRequestProperty("Authorization", "Basic "
                        + encode(authorizationValues.get(AuthorizationValues.NAME) + ":" + authorizationValues.get(AuthorizationValues.PASSWORD)));
                break;
            case BODY:
                if (rp.body != null) {
                    Object body = rp.body;
                    if (body instanceof String) {
                        rp.body = new ObjectMapper().readValue((String) body, Map.class);
                    }
                    if (body instanceof Map) {
                        ((Map) rp.body).put(authorizationValues.get(AuthorizationValues.NAME_FIELD_API_KEY), authorizationValues.get(AuthorizationValues.API_KEY));
                    }
                    // todo check list
                } else {
                    Map<String, Object> bodyMap = new HashMap<>();
                    bodyMap.put(authorizationValues.get(AuthorizationValues.NAME_FIELD_API_KEY), authorizationValues.get(AuthorizationValues.API_KEY));
                    rp.body = bodyMap;
                }
        }
    }

    public void setHeaders(HttpURLConnection connection, AllRequestParameters rp) {
        rp.headers.forEach(connection::addRequestProperty);
    }

    public void setMethod(HttpURLConnection connection, AllRequestParameters rp) throws ProtocolException {
        connection.setRequestMethod(rp.requestMethod);
    }

    public void sendBodyIfExists(HttpURLConnection connection, AllRequestParameters rp) throws IOException {
        if (isHaveBody(rp)) {

            // todo hard code
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = connection.getOutputStream()) {
                if (rp.body != null) {
                    if (rp.body instanceof Map) {
                        String strOut = new ObjectMapper().writeValueAsString(rp.body);
                        byte[] input = strOut.getBytes(StandardCharsets.UTF_8);
                        os.write(input, 0, input.length);
                    } else {
                        throw new RuntimeException("Body is not a Map");
                    }
                }
            }
        }
    }

    // todo may return more options !

    public String getResponse(HttpURLConnection connection) throws IOException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        if (connection.getResponseCode() == 200) {
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                sb.append(strCurrentLine);
            }
        } else {
            br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                sb.append(strCurrentLine);
            }
        }
        return sb.toString();
    }

    private Boolean isHaveBody(AllRequestParameters rp) {
        String m = rp.requestMethod;
        return m.equals("POST") || m.equals("PUT") || m.equals("PATCH");
    }

    private String encode(String str) {
        return new String(Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8)));
    }
}
