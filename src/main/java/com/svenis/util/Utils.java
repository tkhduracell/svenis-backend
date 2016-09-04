package com.svenis.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

  private static final String H2_DATABASE = "jdbc:h2:./target/svenisdb";

  public static Connection conn() throws SQLException {
    return DriverManager.getConnection(H2_DATABASE, "sa", "");
  }
}
