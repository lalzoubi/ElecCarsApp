package com.eleccars.ElecCarsApp.javautils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JavaSpringUtils {

    @Autowired
    private MessageSource messageSource;

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        Date date = new Date();
        try {

            Date currentdate = dateFormat.parse(date.toString());
            SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
            return sdf2.format(currentdate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    public String getMessageSource(String message, String[] args) {
        return messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
    }
}
