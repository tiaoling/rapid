package com.xiaojiuwo.utils;

/**
 * Created by liuhaibao on 15/11/22.
 */
public class Factorial {

    public long factorial(long number) {
        if(number == 0) {
            return 1;
        }
        return number*factorial(number-1);
    }
}
