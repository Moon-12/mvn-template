package com.ashwija.mvn.common;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateAndTime {
    public static Timestamp getCurrentTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }
}
