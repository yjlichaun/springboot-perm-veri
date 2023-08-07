package com.muyi.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 历川
 * @version 1.0
 * @description 时间类
 * @date 2023/8/7 17:30
 */
public class DateUtil {
    
    
    public static String getDateNow() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }
}
