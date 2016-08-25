package com.svenis.util;

import com.google.common.hash.Hashing;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import spark.Response;

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

  public static String sendJson(Response res, String file) {
    try {
      Path path = Paths.get(file);
      byte[] readAllBytes = Files.readAllBytes(path);
      jsonContent(res);
      Charset charset = Charset.defaultCharset();
      return new String(readAllBytes, charset);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void jsonContent(Response res) {
    res.header("Content-type", "application/json; charset=utf-8");
  }
}
