package com.svenis.util;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.Locale;

import static com.svenis.util.RespUtils.json;

public class JsonUtils {

    public static String asJson(String key, String value) {
        return new JSONObject(ImmutableMap.of(key, value)).toString();
    }

    private static JSONObject asJsonType(Record r) {
        JSONObject obj = new JSONObject();
        for (Field<?> field : r.fields()) {
            String fieldName = field.getName().toLowerCase(Locale.ENGLISH);
            obj.put(fieldName, r.get(field));
        }
        return obj;
    }

    private static JSONArray asJsonType(Record[] records) {
        JSONArray arr = new JSONArray();
        for (Record record : records) {
            arr.add(asJsonType(record));
        }
        return arr;
    }

    public static String asJson(Object r) {
        if (r instanceof Record) {
            return asJsonType(Record.class.cast(r)).toString();
        } else if(r instanceof Record[]) {
            return asJsonType(Record[].class.cast(r)).toString();
        } else {
            return new Gson().toJson(r);
        }
    }

    public static void defaultJsonType(Request res, Response resp) {
        json(resp);
    }
}
