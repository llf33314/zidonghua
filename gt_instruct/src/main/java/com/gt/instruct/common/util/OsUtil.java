package com.gt.instruct.common.util;

/**
 * @author linweicong
 * @version 2018-01-17 18:34:14
 */
public class OsUtil {
    public static boolean isLinux() {
        return System.getProperties().getProperty("os.name").toLowerCase().contains("linux");
    }
}
