package com.wentong.api.util;

import java.util.concurrent.TimeUnit;

public class Utils {

    public static void sleepSeconds(int n) {
        try {
            TimeUnit.SECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
