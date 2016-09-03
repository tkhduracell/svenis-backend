package com.svenis.util;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;

import java.util.Locale;

public class JsonUtils {

    private static final Gson GSON = new Gson();

    public static String asJson(String key, String value) {
        return new JSONObject(ImmutableMap.of(key, value)).toString();
    }

    public static String asJson(Object o) {
        return GSON.toJson(o);
    }
}
