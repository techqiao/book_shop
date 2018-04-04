package com.wjq.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/29 14:02
 * <p>@author : wjq
 */
public class MockServer {
    public static void main(String[] args) {
        configureFor("127.0.0.1", 8080);
        stubFor(get(urlEqualTo("/book")).willReturn(okJson("{'name':'jack'}")));
    }
}
