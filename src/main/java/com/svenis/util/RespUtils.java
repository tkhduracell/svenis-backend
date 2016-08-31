package com.svenis.util;

import spark.Response;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RespUtils {

  public static String sendJsonFile(Response res, String file) {
    try {
      Path path = Paths.get(file);
      byte[] readAllBytes = Files.readAllBytes(path);
      json(res);
      Charset charset = Charset.defaultCharset();
      return new String(readAllBytes, charset);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void json(Response res) {
    res.header("Content-type", "application/json; charset=utf-8");
  }

  public static void html(Response res) {
    res.header("Content-type", "text/html; charset=utf-8");
  }
}
