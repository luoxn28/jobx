package com.luo.jobx.executor.util;

import com.xiaoleilu.hutool.io.FileUtil;

/**
 * 系统信息帮助类
 *
 * @author xiangnan
 */
public class SystemUtil {

    // linux window 系统文件路径分隔符
    public static final char linuxSeparator = '/';
    public static final char windowSeparator = '\\';
    public static char systemSeparator = linuxSeparator;

    // 当前应用的运行目录
    public static String applicationPath = "";

    static {
        if (isWindows()) {
            systemSeparator = windowSeparator;
        }

        applicationPath = System.getProperty("user.dir");
    }

    public static boolean isWindows() {
        return FileUtil.isWindows();
    }

}
