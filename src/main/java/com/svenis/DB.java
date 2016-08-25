package com.svenis;

import static com.svenis.util.Utils.ln;

import java.sql.Connection;
import java.sql.DriverManager;
import org.h2.tools.Server;

public class DB {

  private static final String STRING = "jdbc:h2:tcp://localhost/./target/svenisdb";

  public static Server start() {
    try {
      Server server = Server.createTcpServer("-tcpAllowOthers").start();
      Class<?> aClass = Class.forName("org.h2.Driver");
      Connection conn = DriverManager.getConnection(STRING, "sa", "");
      String productName = conn.getMetaData().getDatabaseProductName();
      ln("Connection established: %s", productName + "/" + conn.getCatalog());
      Thread t = new Thread(() -> {
        ln("Shutting down!");
        server.shutdown();
        ln("Goodbye!");
      });
      Runtime.getRuntime().addShutdownHook(t);
      return server;
    } catch (Exception e) {
      throw new RuntimeException("Failed to start app database", e);
    }
  }
}
