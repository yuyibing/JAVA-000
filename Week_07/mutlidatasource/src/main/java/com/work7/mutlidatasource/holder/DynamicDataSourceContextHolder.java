package com.work7.mutlidatasource.holder;

/**
 * 通过ThradLocal 获取和设置线程安全的key
 */
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static synchronized void setDataSourceKey(String key){
        contextHolder.set(key);
    }

    public static String getDataSourceKey(){
        return contextHolder.get();
    }

    public static void clearDataSourceKey(){
        contextHolder.remove();
    }
}
