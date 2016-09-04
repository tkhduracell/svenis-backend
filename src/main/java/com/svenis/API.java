package com.svenis;

import com.google.common.collect.ImmutableList;
import com.svenis.model.svenis.tables.*;
import com.svenis.util.AuthUtils;
import com.svenis.util.JsonUtils;
import com.svenis.util.RouteDef;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.impl.DSL;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.route.HttpMethod;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static com.svenis.util.JsonUtils.asJson;
import static com.svenis.util.RespUtils.html;
import static com.svenis.util.RespUtils.sendJsonFile;
import static com.svenis.util.RouteDef.def;
import static com.svenis.util.Utils.conn;
import static spark.Spark.*;

public class API {

  private static final List<RouteDef> ROUTES = ImmutableList.of(
    def(HttpMethod.get, "/",  "Returns this page [html]"),
    def(HttpMethod.get, "/raw", "test page"),
    def(HttpMethod.get, "/user",  "Returns user [json] ?session_id=<?> "),
    def(HttpMethod.get, "/theme", "Returns theme [json]"),
    def(HttpMethod.get, "/puzzle", "Returns puzzle [json]"),
    def(HttpMethod.get, "/session", "Returns session [json]"),
    def(HttpMethod.get, "/schedule", "Returns schedule [json]"),
    def(HttpMethod.get, "/question", "Returns questions [json]"),
    def(HttpMethod.get, "/geocache", "Returns geocache [json]")
  );

  private static final int PORT = 9999;


  public static void start() {
    exception(Exception.class, (e, req, res) -> e.printStackTrace());
    port(PORT);

    before(AuthUtils::checkAuthenticate); // Make authentication happen
    before(JsonUtils::defaultJsonType); // Json default return type

    get("/", API::renderMain);
    get("/raw", (req,res) -> sendJsonFile(res, "src/main/resources/main.json"));

    get("/session", oneFromSession(Sessions.SESSIONS), JsonUtils::asJson);

    get("/theme", oneFromSession(Theme.THEME), JsonUtils::asJson);
    get("/puzzle",  allFromSession(Puzzle.PUZZLE), JsonUtils::asJson);
    get("/mission", allFromSession(Missions.MISSIONS), JsonUtils::asJson);
    get("/schedule", allFromSession(Schedule.SCHEDULE), JsonUtils::asJson);
    get("/geocache", allFromSession(Geocache.GEOCACHE), JsonUtils::asJson);
  }

  private static int getSessionId(Request req) {
    String idParam = req.queryParams("id");
    try {
      return Integer.parseInt(idParam);
    } catch (NumberFormatException e) {
      halt(500, asJson("error", "Invalid id '" + idParam + "'"));
      return -1;
    }
  }

  private static <R extends Record> Route oneFromSession(Table<R> type) {
    return (req, resp) -> oneFromSession(type, getSessionId(req));
  }

  private static <R extends Record> R oneFromSession(Table<R> type, Integer sessionId) {
    try (Connection c = conn()) {
      return DSL.using(c)
              .selectFrom(type)
              .where("session_id = ?", sessionId)
              .fetchOptional()
              .orElse(null);
    } catch (SQLException e) {
      halt(500, e.toString());
      return null;
    }
  }

  private static <R extends Record> Route allFromSession(Table<R> type) {
    return (req, resp) -> allFromSession(type, getSessionId(req));
  }

  private static <R extends Record> R[] allFromSession(Table<R> type, Integer sessionId) {
    try (Connection c = conn()) {
      return DSL.using(c)
              .selectFrom(type)
              .where("session_id = ?", sessionId)
              .fetchArray();
    } catch (SQLException e) {
      halt(500, e.toString());
      return null;
    }
  }

  private static String renderMain(Request req, Response res) {
    String routes = ROUTES.stream()
            .map(e -> String.format("\t<li><a href='%s'>%s %s\t-> %s</a></li>",
                    e.path(),
                    e.verb().name(),
                    e.path(),
                    e.doc())
            )
            .collect(Collectors.joining("\n"));

    html(res);
    return "<h1>Welcome to Svenis!</h1>" +
    "<p>" +
      "Spec: " +
      "<a href=\"http://private-80d637-markuslarsson.apiary-mock.com/questions\">" +
        "http://private-80d637-markuslarsson.apiary-mock.com/questions" +
      "</a>" +
    "</p>"  +
    "<h2>Authentication</h2>" +
    "<p>In the first request you have to login using <code>/?user=&lt;your user&gt;&pass=&lt;your pass&gt;</code> this will not be necessary on subsequent requests.</p>" +
    "<h2>Available routes</h2>" +
    "<ul>" + routes + "<ul>";
  }
}
