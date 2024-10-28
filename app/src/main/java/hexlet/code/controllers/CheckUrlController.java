package hexlet.code.controllers;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.CheckUrlRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.utils.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.sql.SQLException;


@Slf4j
public class CheckUrlController {

    public static void check(Context ctx) throws SQLException {

        String currentId = ctx.pathParam("id");

        try {
            Long urlId = ctx.pathParamAsClass("id", Long.class).get();
            Url url = UrlRepository.findById(urlId)
                    .orElseThrow(() -> new NotFoundResponse("Не удалось найти адрес"));

            HttpResponse<String> bodyResponse = Unirest.get(url.getName()).asString();
            int status = bodyResponse.getStatus();
            String body = bodyResponse.getBody();
            Document document = Jsoup.parse(body);
            String title = document.title();
            String h1 = document.getElementsByTag("h1").text();
            String description = document.select("meta[name=description]").attr("content");

            UrlCheck check = new UrlCheck(status, title, h1, description, urlId);
            CheckUrlRepository.save(check);

            ctx.sessionAttribute("flash", "Страница успешно проверена");
            ctx.sessionAttribute("flashType", "success");
        } catch (Exception e) {

            ctx.sessionAttribute("flash", "Не удалось осуществить проверку");
            ctx.sessionAttribute("flashType", "danger");
        }
        ctx.redirect(NamedRoutes.urlPath(currentId));
    }
}
