package com.cc.musiclist.util;

import android.text.TextUtils;

import java.io.File;

/**
 * Created by zhangyu on 2016-06-21 10:53.
 */
public class StringUtil {
    /**
     * 根据ascii码比较字符串的大小
     * @param strA
     * @param strB
     * @return
     */
    public static int compare(String strA ,String strB){
        for (int i = 0; i < strA.length(); i++) {
            if (i < strB.length()) {
                char a = strA.charAt(i);
                char b = strB.charAt(i);
                //比较ascii码
                if (a > b)
                    return 1;
                else if (a == b)
                    continue;
                else return -1;
            } else
                return 1;
        }
        return 0;
    }

    /**
     * 秒值转化为00:00格式字符串
     *
     * @param second
     * @return
     */
    public static String msToTime(long second) {
        StringBuffer sb = new StringBuffer();
        long min = second / 60;
        long s = second % 60;

        if (min < 10)
            sb.append("0").append(min);
        else
            sb.append(min);

        sb.append(":");

        if (s < 10)
            sb.append("0").append(s);
        else
            sb.append(s);
        return sb.toString();
    }

    public static String subPostfix(String str){
        if(!TextUtils.isEmpty(str) && str.contains(".")) {
            int index = str.lastIndexOf(".");
            return str.substring(0, index);
        }else
            return str;
    }

    public static boolean isMobileNO(String mobiles) {
        if (mobiles == null) {
            return false;
        }
        return mobiles.matches("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
    }

    public static boolean isEmil(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
    }

}
