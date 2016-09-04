package com.svenis.util;

import spark.route.HttpMethod;

public class RouteDef {
    private final HttpMethod verb;
    private final String path;
    private final String doc;

    private RouteDef(HttpMethod verb, String path, String doc) {
        this.verb = verb;
        this.path = path;
        this.doc = doc;
    }

    public HttpMethod verb() {
        return verb;
    }

    public String path() {
        return path;
    }

    public String doc() {
        return doc;
    }

    public static RouteDef def(HttpMethod verb, String path, String doc) {
        return new RouteDef(verb, path, doc);
    }
}