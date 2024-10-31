package hexlet.code.repository;

import hexlet.code.model.UrlCheck;
import hexlet.code.utils.FormattedTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CheckUrlRepository extends BaseRepository {
    public static void save(UrlCheck urlCheck) throws SQLException {
        String sql = """
        INSERT INTO url_checks (url_id, status_code, h1, title, description, created_at)
        VALUES (?, ?, ?, ?, ?, ?);
            """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            var urlId = urlCheck.getUrlId();
            var statusCode = urlCheck.getStatusCode();
            var h1 = urlCheck.getH1();
            var title = urlCheck.getTitle();
            var description = urlCheck.getDescription();
            var createdAt = FormattedTime.getCurrentTime();

            preparedStatement.setLong(1, urlId);
            preparedStatement.setInt(2, statusCode);
            preparedStatement.setString(3, h1);
            preparedStatement.setString(4, title);
            preparedStatement.setString(5, description);
            preparedStatement.setTimestamp(6, Timestamp.valueOf(createdAt));
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getLong("id"));
                urlCheck.setCreatedAt(createdAt);
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static Map<Long, UrlCheck> getLastChecks() throws SQLException {
        String sql = """
                SELECT DISTINCT ON (url_id) * FROM url_checks
                ORDER BY url_id DESC, id DESC
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            var resultSet = preparedStatement.executeQuery();
            var result = new HashMap<Long, UrlCheck>();

            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var urlId = resultSet.getLong("url_id");
                var statusCode = resultSet.getInt("status_code");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();

                UrlCheck urlCheck = new UrlCheck(statusCode, title, h1, description, urlId);
                urlCheck.setId(id);
                urlCheck.setUrlId(urlId);
                urlCheck.setCreatedAt(createdAt);
                result.put(urlId, urlCheck);
            }
            return result;
        }
    }

    public static List<UrlCheck> getEntitiesForCurrentUrl(Long urlId) throws SQLException {
        String sql = "SELECT * FROM url_checks WHERE url_id = ? ORDER BY id DESC;";
        List<UrlCheck> urlChecks = new ArrayList<>();

        try (var conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, urlId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                var id = resultSet.getLong("id");
                var statusCode = resultSet.getInt("status_code");
                var h1 = resultSet.getString("h1");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();

                UrlCheck urlCheck = new UrlCheck(statusCode, title, h1, description, urlId);
                urlCheck.setId(id);
                urlCheck.setCreatedAt(createdAt);
                urlChecks.add(urlCheck);
            }
            return urlChecks;
        }
    }
}
