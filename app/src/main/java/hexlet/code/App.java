package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.controllers.CheckUrlController;
import hexlet.code.controllers.RootController;
import hexlet.code.controllers.UrlController;
import hexlet.code.repository.BaseRepository;
import hexlet.code.utils.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.stream.Collectors;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;


@Slf4j
public class App {
    public static Javalin getApp() throws IOException, SQLException {
        // Создание БД Н2 в памяти
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getDataBaseUrl());
        var dataSource = new HikariDataSource(hikariConfig);

        //Получение пути до файла src/main/resources/schema.sql,
        // содержащий sql-запросы на создание таблиц в БД
        var url = App.class.getClassLoader().getResourceAsStream("schema.sql");
        var sql = new BufferedReader(new InputStreamReader(url))
                .lines()
                .collect(Collectors.joining("\n"));
        log.info(sql);

        // Установка соединения и инициализация БД через стейтмент
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

        app.get(NamedRoutes.rootPath(), RootController::indexRoot);
        app.post(NamedRoutes.urlsPath(), UrlController::create);
        app.get(NamedRoutes.urlsPath(), UrlController::index);
        app.get(NamedRoutes.urlPath("{id}"), UrlController::show);
        app.post(NamedRoutes.urlCheckPath("{id}"), CheckUrlController::check);


        return app;
    }

    private static String getDataBaseUrl() {
        String url = System.getenv().getOrDefault("JDBC_DATABASE_URL",
                "jdbc:h2:mem:project");
        return url;
    }

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.parseInt(port);
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

    public static void main(String[] args) throws SQLException, IOException {
        Javalin app = getApp();
        app.start(getPort());
    }
}
