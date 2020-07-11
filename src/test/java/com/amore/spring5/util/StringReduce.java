package com.amore.spring5.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReduce {

    private static final Pattern SPECIAL_NUM_SYMBOL = Pattern.compile("[0-9]*");

    public static void main(String[] args) {
//        String abc = "23gfdsgdsg";
//        Matcher matcher = SPECIAL_NUM_SYMBOL.matcher(abc);
//        while (matcher.find()) {
//            System.out.println(matcher.group());
//        }
        String str = "AS[2ABC[3DEF]OX[1VC]]GG";
        String ret = getReduceStr(str);

        System.out.println(ret);
    }

    public static String getReduceStr(String source) {
        if (source != null && !source.isEmpty()) {
            int lastIndexOf = source.lastIndexOf("[");
            System.out.println("lastIndexOf: " + lastIndexOf);
            if (lastIndexOf != -1) {
                int firstIndexOf = source.substring(lastIndexOf).indexOf("]");
                String subStr = source.substring(lastIndexOf + 1, lastIndexOf + firstIndexOf);
                System.out.println("subStr:" + subStr);
                if (subStr != null) {
                    Matcher matcher = SPECIAL_NUM_SYMBOL.matcher(subStr);
                    int num = 1;
                    String reg = "";
                    if (matcher.find()) {
                        reg = matcher.group();
                        num = Integer.parseInt(reg);
                    }
                    String sub = subStr.substring(subStr.lastIndexOf(reg) + 1);
                    StringBuffer sb = new StringBuffer();
                    System.out.println("reg:" + reg + " : " + "num: " + num + " : " + "sub: " + sub);
                    for (int i = 0; i < num; i++) {
                        sb.append(sub);
                    }
                    String ret = source.substring(0, lastIndexOf) + sb.toString() + source.substring(lastIndexOf + firstIndexOf + 1);
                    System.out.println("ret :" + ret);
                    return getReduceStr(ret);
                }
            }
            return source;
        }
        return null;
    }

}
