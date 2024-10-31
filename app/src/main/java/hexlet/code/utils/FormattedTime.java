package hexlet.code.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FormattedTime {
    public static LocalDateTime getCurrentTime() {
        Instant instant = Instant.now();
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    public static String getFormattedTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }
}
