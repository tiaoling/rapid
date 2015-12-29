package com.xiaojiuwo.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import static com.alibaba.druid.filter.config.ConfigTools.*;

/**
 * Created by liuhaibao on 15/11/20.
 */
public class Encode {
    public static void en(){
        try {
            String aa = encrypt("aaaa");
            System.out.println(aa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
