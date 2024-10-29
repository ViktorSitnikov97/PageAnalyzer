package hexlet.code;

import hexlet.code.model.Url;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.CheckUrlRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.utils.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppTest {

    private Javalin app;
    private static MockWebServer mockServer;
    private static String mockUrl;

    @BeforeAll
    public static void setUpAll() throws Exception {
        mockServer = new MockWebServer();
        MockResponse mockResponse = new MockResponse();
        String testHtmlPageBody = Files.readString(Paths.get("src/test/resources/test.html"));
        mockResponse.setBody(testHtmlPageBody); // установка ожидаемого тела ответа
        mockServer.enqueue(mockResponse); // установка в очередь ожидаемого ответа
        mockServer.start();
        mockUrl = mockServer.url("/").toString(); // получение URL-адреса мок-сервера
        // адрес вида http://localhost:12345/) для последующей проверки на SEO-пригодность
    }

    @BeforeEach
    public final void setUpEach() throws Exception {
        app = App.getApp();
    }

    @AfterAll
    public static void stop() throws IOException {
        mockServer.shutdown();
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
            response.close();
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
    @Test
    public void testUrlCheck() {
        JavalinTest.test(app, (server, client) -> {
            String requestBody = "url=" + mockUrl;
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertThat(response.code()).isEqualTo(200);

            String responseBodyString = response.body().string();
            assertThat(responseBodyString).contains(mockUrl.substring(0, mockUrl.length() - 1));

            var id = 1L;
            response = client.post(NamedRoutes.urlCheckPath(id));
            responseBodyString = response.body().string();

            assertThat(response.code()).isEqualTo(200);
            assertThat(responseBodyString).contains("Check title");
            assertThat(responseBodyString).contains("Check description");
            assertThat(responseBodyString).contains("Check h1");

            UrlCheck urlCheck = CheckUrlRepository.getLastCheck(id).get();

            assertEquals(id, urlCheck.getUrlId());
            assertEquals("Check title", urlCheck.getTitle());
            assertEquals("Check description", urlCheck.getDescription());
            assertEquals("Check h1", urlCheck.getH1());

        });
    }
}
