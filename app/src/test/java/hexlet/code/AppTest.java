package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.utils.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


public class AppTest {

    Javalin app;

    @BeforeEach
    public final void setUp() throws SQLException, IOException {
        app = App.getApp();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.rootPath());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Бесплатно проверяйте сайты на SEO пригодность");
            response.close();
        });
    }

    @Test
    public void testCreateUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://github.com";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https");
            assertThat(UrlRepository.existsByUrl("https://github.com")).isTrue();
        });
    }

    @Test
    public void testUrlsPage() {
        JavalinTest.test(app, (server, client) -> {
            var request = "url=https://github.com";
            client.post(NamedRoutes.urlsPath(), request);
            var response = client.get(NamedRoutes.urlsPath());

            var responseBody = response.body().toString();
            assertThat(response.code()).isEqualTo(200);
            assertThat(responseBody.contains("github.com"));
            assertThat(UrlRepository.existsByUrl("https://github.com")).isTrue();
            response.close();
        });
    }

    @Test
    public void testUrlPage() throws SQLException {
        Url url = new Url("https://github.com");
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlPath(url.getId()));
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://github.com");
            response.close();
        });
    }

    @Test
    public void negativeTestUrlNotFound() throws SQLException {
        UrlRepository.delete(1234567L);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/1234567");
            assertThat(response.code()).isEqualTo(404);
            response.close();
        });
    }
}
