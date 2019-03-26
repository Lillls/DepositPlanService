package com.lixxy.depositplan.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateUtils {

    /**
     * 参数都是元,但是会*100
     * @param startMoney 起始金额
     * @param increaseMoney  递增金额 输入元
     * @param totalDays 多少天
     * @return 返回乘以100的书
     */
    public static List<Long> generateList(Long startMoney, Long increaseMoney, int totalDays) {
        List<Long> list = new ArrayList<>();
        list.add(startMoney);
        for (int i = 0; i < totalDays; i++) {
            list.add(startMoney += increaseMoney);
        }
        return list;
    }

    public static void main(String[] args) {
        List<Long> list = generateList(100L, 100L, 364);
        System.out.println(list);
    }


}


