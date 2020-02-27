package com.dtobuilder.sender;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Params {

    private final AllRequestParameters rp;

    public Params paramsContentTypeApplicationJson() {
        rp.params.put("Content-Type", "application/json");
        return new Params(rp);
    }

    public Params params(String key, String value) {
        rp.params.put(key, value);
        return new Params(rp);
    }

    public Body lastParam(String key, String value) {
        rp.params.put(key, value);
        return new Body(rp);
    }

    public Body finishParams() {
        return new Body(rp);
    }
}
