package com.chemi.lab.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class DateFormater {


    public String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy HH:mm");
        // Format the LocalDateTime to a String
        return date.format(formatter);
    }
    public  LocalDateTime rangeDate(String dateString,String startOrEnd) {
        System.out.println(dateString + " " + startOrEnd);
        if (Objects.isNull(dateString)) { //hardcode date if date is empty
            if (startOrEnd.equals("start")){
                return LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0, 0);
            }
            if (startOrEnd.equals("end")){
                return LocalDateTime.now();
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        System.out.println(dateTime + " date");
        return dateTime;
    }

}
