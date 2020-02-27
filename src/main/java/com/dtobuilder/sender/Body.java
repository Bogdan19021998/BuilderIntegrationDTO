package com.dtobuilder.sender;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Body {

    private final AllRequestParameters rp;

    public Sender body(Object obj) {
        rp.body = obj;
        return new Sender(rp);
    }

    public Sender skipBody() {
        return new Sender(rp);
    }
}
