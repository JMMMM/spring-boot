package com.dbconfig;

public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> keyThreadLocal = new ThreadLocal<>();

    public static String chooseDataSource() {
        return keyThreadLocal.get();
    }

    public static void setChooseType(String type) {
        keyThreadLocal.set(type);
    }

    public static void clean() {
        keyThreadLocal.remove();
    }
}
