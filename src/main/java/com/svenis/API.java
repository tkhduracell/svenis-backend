package com.svenis;

import com.google.common.collect.ImmutableMap;
import com.svenis.model.svenis.tables.Questions;
import com.svenis.model.svenis.tables.records.QuestionsRecord;
import com.svenis.util.JsonUtils;
import org.jooq.Record;
import org.jooq.impl.DSL;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.route.RouteOverview;
import spark.servlet.SparkApplication;
import spark.utils.SparkUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import static com.svenis.util.JsonUtils.*;
import static com.svenis.util.RespUtils.*;
import static com.svenis.util.Utils.*;
import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.port;

public class API {

  private static final Map<String, String> USERS = ImmutableMap.of(
      "test", sha1("2846")
  );

  private static final int PORT = 9999;
  private static final String QUERY_PARAM_USER = "user";
  private static final String QUERY_PARAM_PASS = "pass";
  private static final String H2_DATABASE = "jdbc:h2:./target/svenisdb";

  public static void start() {
    exception(Exception.class, (e, req, res) -> e.printStackTrace());
    port(PORT);

    before(API::checkAuthenticate);

    // Json default return type
    before((req, res) -> json(res));

    get("/", API::renderMain);
    get("/questions", API::renderUser);
    get("/test", API::renderTest, JsonUtils::asJson);
  }

  private static QuestionsRecord renderTest(Request req, Response res) {
    try (Connection c = DriverManager.getConnection(H2_DATABASE, "sa", "")) {
      return DSL.using(c)
              .selectFrom(Questions.QUESTIONS)
              .fetchAny();
    } catch (SQLException e) {
      halt(500, e.toString());
      return null;
    }
  }

  private static void checkAuthenticate(Request req, Response res) {
    final String user = opt(req.queryParams(QUERY_PARAM_USER), "");
    final String pass = opt(req.queryParams(QUERY_PARAM_PASS), "");
    final boolean isRoot = req.pathInfo().equals("/");
    if (!isRoot && !USERS.getOrDefault(user, "").equals(sha1(pass))) {
      halt(401, asJson("message", "Invalid credentials"));
    }
  }

  private static String renderUser(Request req, Response res) {
    return sendJsonFile(res, "src/main/resources/main.json");
  }

  private static String renderMain(Request req, Response res) {
    html(res);
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
