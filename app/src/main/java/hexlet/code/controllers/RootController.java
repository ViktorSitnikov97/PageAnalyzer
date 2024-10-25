package hexlet.code.controllers;

import io.javalin.http.Context;

public class RootController {
    public static void indexRoot(Context ctx) {
        ctx.render("index.jte");
    }
}
