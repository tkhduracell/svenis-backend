package com.svenis.util;

import com.google.common.collect.ImmutableMap;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;

import java.util.Locale;

public class JsonUtils {

  private static JSONObject asJsonType(Record r) {
    JSONObject obj = new JSONObject();
    for (Field<?> field : r.fields()) {
      String fieldName = field.getName().toLowerCase(Locale.ENGLISH);
      obj.put(fieldName, r.get(field));
    }
    return obj;
  }


  public static String asJson(Record r) {
    return asJsonType(r).toString();
  }

  public static String asJson(Record[] records) {
    JSONArray array = new JSONArray();
    for (Record r : records) {
      array.add(asJsonType(r));
    }
    return array.toString();
  }

  public static String asJson(String key, String value) {
    return JSONObject.toJSONString(ImmutableMap.of(key, value));
  }

}
