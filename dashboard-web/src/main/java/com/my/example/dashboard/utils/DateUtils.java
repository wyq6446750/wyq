package com.my.example.dashboard.utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * Date:17/2/7
 * Time:下午6:25
 *
 * @author yongquan.wen@corp.elong.com
 */
public class DateUtils {

    private void fastDateFormat() {
        FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd");
        System.out.println(fdf.format(new Date()));
        try {
            Date date = fdf.parse("2014-01-30");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        DateUtils dateUtils = new DateUtils();
        dateUtils.fastDateFormat();
    }

}
