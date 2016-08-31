package com.svenis.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * Created by filiplindqvist on 15/08/16.
 */
public class Utils {

  public static void ln(String format, Object... args) {
    System.out.printf(format.concat("%n"), args);
  }

  public static String sha1(String pass) {
    return Hashing.sha256()
        .hashString(pass, StandardCharsets.UTF_8)
        .toString();
  }

  public static <T> T opt(T value, T defaultValue) {
    return value == null ? defaultValue : value;
  }
}
