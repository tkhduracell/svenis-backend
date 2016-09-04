package com.svenis.util;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import spark.Request;
import spark.Response;

import java.util.Map;
import java.util.Set;

import static com.svenis.util.JsonUtils.asJson;
import static com.svenis.util.Utils.opt;
import static com.svenis.util.Utils.sha1;
import static spark.Spark.halt;

public class AuthUtils {

    private static final String QUERY_PARAM_USER = "user";
    private static final String QUERY_PARAM_PASS = "pass";

    private static final Map<String, String> USERS = ImmutableMap.of(
            "test", sha1("2846")
    );

    private static final Set<String> OPEN_PATHS = ImmutableSet.of(
            "/"
    );

    public static void checkAuthenticate(Request req, Response res) {
        final String user = opt(req.queryParams(QUERY_PARAM_USER), req.session().attribute("user"));
        final String pass = opt(req.queryParams(QUERY_PARAM_PASS), req.session().attribute("pass"));
        final boolean isOpen = OPEN_PATHS.contains(req.pathInfo());
        final boolean isAuth = !Strings.isNullOrEmpty(user) &&
                USERS.getOrDefault(user, "").equals(sha1(pass));

        if (isOpen) {
            return;
        }
        if (isAuth) {
            req.session().attribute("user", user);
            req.session().attribute("pass", pass);
        } else {
            halt(401, asJson("message", "Invalid credentials"));
            req.session().invalidate();
        }
    }

}
