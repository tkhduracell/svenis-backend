package com.svenis;

import static com.svenis.util.Utils.opt;
import static com.svenis.util.Utils.sendJson;
import static com.svenis.util.Utils.sha1;
import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.port;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import spark.Request;
import spark.Response;

public class API {

  private static final Map<String, String> USERS = ImmutableMap.of(
      "test", sha1("password")
  );

  private static final int PORT = 9999;
  private static final String QUERY_PARAM_USER = "user";
  private static final String QUERY_PARAM_PASS = "pass";

  public static void start() {
    exception(Exception.class, (e, req, res) -> e.printStackTrace());
    port(PORT);

    before(API::checkAuthenticate);

    get("/", API::renderMain);
    get("/user", API::renderUser);
  }

  private static void checkAuthenticate(Request req, Response res) {
    final String user = opt(req.queryParams(QUERY_PARAM_USER), "");
    final String pass = opt(req.queryParams(QUERY_PARAM_PASS), "");
    final boolean isRoot = req.pathInfo().equals("/");
    if (!isRoot && !USERS.getOrDefault(user, "").equals(sha1(pass))) {
      halt(401, "Invalid credentials");
    }
  }

  private static String renderUser(Request req, Response res) {
    return sendJson(res, "src/main/resources/main.json");
  }

  private static String renderMain(Request req, Response res) {
    res.header("Content-type", "text/html; charset=utf-8");
    return "<h1>Welcome!</h1>" +
    "<p>" +
      "<a href=\"http://private-80d637-markuslarsson.apiary-mock.com/questions\">" +
      "http://private-80d637-markuslarsson.apiary-mock.com/questions" +
      "</a>" +
    "</p>" +
    "<pre>" +
           "\tGET /     -> Returns this page\n" +
           "\tGET /user -> Returns user info as json\n" +
    "<pre>";
  }
}
