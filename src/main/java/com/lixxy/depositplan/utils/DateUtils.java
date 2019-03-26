package com.lixxy.depositplan.utils;

import java.util.Date;

public class DateUtils {

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static int getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (int) ((afterTime - beforeTime) / (1000 * 60 * 60 * 24));
    }
}
