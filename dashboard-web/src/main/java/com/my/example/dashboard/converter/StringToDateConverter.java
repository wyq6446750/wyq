package com.my.example.dashboard.converter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by user on 16/8/23.
 */
public class StringToDateConverter implements Converter<String, Date>{
    private String[] parsePatterns;

    public StringToDateConverter(String[] parsePatterns) {
        this.parsePatterns = parsePatterns;
    }

    @Override
    public Date convert(String date) {
        if(StringUtils.isBlank(date)){
            return null;
        }
        try {
            return DateUtils.parseDate(date, Locale.CHINESE, parsePatterns);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
