package com.udemy_3.daychecked_beta5.Convertor;


import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class LocalDateTypeConvertor {


    @TypeConverter
    public static LocalDate toDate(String dateString) {
        if (dateString == null) {
            return null;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return LocalDate.parse(dateString);
            }
        }
        return null;
    }

    @TypeConverter
    public static String toDateString(LocalDate date) {
        if (date == null) {
            return null;
        } else {
            return date.toString();
        }
    }
}
