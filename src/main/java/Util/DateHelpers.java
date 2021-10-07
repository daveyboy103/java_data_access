package Util;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateHelpers {
    public static LocalDateTime convertToLocalDateTime(Date date) {
        return Instant
                .ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
