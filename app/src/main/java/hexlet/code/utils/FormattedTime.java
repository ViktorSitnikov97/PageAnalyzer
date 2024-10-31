package hexlet.code.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FormattedTime {
    public static LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    public static String getFormattedTime(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        return zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }
}
