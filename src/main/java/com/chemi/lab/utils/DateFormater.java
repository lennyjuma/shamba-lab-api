package com.chemi.lab.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateFormater {
    public String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy HH:mm");

        // Format the LocalDateTime to a String
        return date.format(formatter);
    }
}
