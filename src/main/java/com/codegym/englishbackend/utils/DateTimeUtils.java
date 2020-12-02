package com.codegym.englishbackend.utils;

import org.springframework.http.converter.json.GsonBuilderUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
    public static Date changeFromStringToDate(String dateString) throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(dateString);
        return date;
    }

    public static void main(String[] args) throws ParseException {
        Date a = DateTimeUtils.changeFromStringToDate("01/12/2020");
        System.out.println(a);
    }
}
