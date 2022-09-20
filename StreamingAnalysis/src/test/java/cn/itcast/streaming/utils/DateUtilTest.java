package cn.itcast.streaming.utils;

import static cn.itcast.streaming.utils.DateUtil.*;

public class DateUtilTest {
    public static void main(String[] args) {
        System.out.println(getCurrentDateTime());
        System.out.println(getCurrentDate());
        System.out.println(convertStringToDate("20210301"));
        System.out.println(convertStringToDateTime("2021-03-01 10:21:32"));
        System.out.println(convertStringToDateString("2021-03-01 10:21:32"));
    }
}
