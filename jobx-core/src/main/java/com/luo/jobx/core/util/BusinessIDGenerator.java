package com.luo.jobx.core.util;

import com.xiaoleilu.hutool.date.DateUtil;

import java.util.Date;
import java.util.Random;

/**
 * ID生成器
 *
 * @author xiangnan
 */
public class BusinessIDGenerator {

    private static final Random random = new Random();

    public synchronized static String getId() {
        return DateUtil.format(new Date(), "yyMMddHHmmss") +
                getRandom() + getRandom();
    }

    private static int getRandom() {
        int r = random.nextInt(10000);

        while (r < 1000)
            r = random.nextInt(10000);

        return r;
    }
}
