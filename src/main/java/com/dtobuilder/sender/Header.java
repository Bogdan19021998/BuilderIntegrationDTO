package com.dtobuilder.sender;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Header {

    private final AllRequestParameters rp;

    public Header header(String key, String value) {
        rp.headers.put(key, value);
        return new Header(rp);
    }

    public Params lastHeader(String key, String value) {
        rp.headers.put(key, value);
        return new Params(rp);
    }

    public Params finishHeader() {
        return new Params(rp);
    }
}
