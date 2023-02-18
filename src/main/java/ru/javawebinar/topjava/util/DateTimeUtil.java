package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static @Nullable LocalDate parseLocalDate(@Nullable String parameter) {
        return StringUtils.hasLength(parameter) ? LocalDate.parse(parameter) : null;
    }

    public static @Nullable LocalTime parseLocalTime(@Nullable String parameter) {
        return StringUtils.hasLength(parameter) ? LocalTime.parse(parameter) : null;
    }
}

