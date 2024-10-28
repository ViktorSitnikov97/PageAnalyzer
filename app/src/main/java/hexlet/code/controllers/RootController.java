package hexlet.code.controllers;

import hexlet.code.dto.BasePage;
import io.javalin.http.Context;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RootController {
    public static void indexRoot(Context ctx) {
        BasePage page = new BasePage();
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        ctx.render("index.jte", model("page", page));
    }
}
