package hexlet.code.controllers;

import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.CheckUrlRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.utils.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlController {

    public static void index(Context ctx) throws SQLException {
        List<Url> urls = UrlRepository.getEntities();
        Map<Long, UrlCheck> lastChecks = CheckUrlRepository.getLastChecks();

        UrlsPage page = new UrlsPage(urls, lastChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flashType"));

        ctx.render("urls/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        Url url = UrlRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("Site with id = " + id + " not found"));

        List<UrlCheck> checks = CheckUrlRepository.getEntitiesForCurrentUrl(id);
        UrlPage page = new UrlPage(url);
        page.setChecks(checks);

        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        ctx.render("urls/show.jte", model("page", page));
    }

    private static String normalizedUrl(String url) throws URISyntaxException {
        URI uriUrl = new URI(url);
        return uriUrl.getScheme() + "://" + uriUrl.getAuthority();
    }

    public static void create(Context ctx) throws SQLException {

        String currentUrl = ctx.formParam("url");
        URL resultUrl = null;
        String urlName = null;

        try {
            URI uri = new URI(currentUrl);
            resultUrl = uri.toURL();
            urlName = normalizedUrl(resultUrl.toString());
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Некорректный адрес");
            ctx.sessionAttribute("flashType", "danger");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        if (UrlRepository.existsByUrl(urlName)) {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.sessionAttribute("flashType", "info");
        } else {
            ctx.sessionAttribute("flash", "Страница успешно добавлена");
            ctx.sessionAttribute("flashType", "success");
            Url url = new Url(urlName);
            UrlRepository.save(url);
        }
        ctx.redirect(NamedRoutes.urlsPath());
    }

    public static void destroy(Context ctx) throws SQLException {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        UrlRepository.delete(id);
        ctx.sessionAttribute("flash", "Страница успешно удалена");
        ctx.sessionAttribute("flashType", "success");
        ctx.redirect(NamedRoutes.urlsPath());
    }

}
