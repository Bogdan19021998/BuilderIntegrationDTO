package com.dtobuilder.sender;

public class Request {

    private AllRequestParameters rp = new AllRequestParameters();

    public static Request buildPattern() {
        return new Request();
    }

    // todo add custom part for url sss/sss/id/sss/name ...
    public RequestMethod baseURL(String url) {
        rp.baseUrl = url;
        return new RequestMethod(rp);
    }
}
