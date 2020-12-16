package com.work7.mutlidatasource.datasource;

import com.work7.mutlidatasource.holder.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Component
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

//    private final Logger logger = LoggerFactory.getLogger(getClass());

    // 单例句柄
    private static volatile DynamicDataSource instance;
    private static byte[] lock = new byte[0];
    // 用于存储已实例化的数据源map
    private static Map<Object, Object> dataSourceMap = new HashMap<>();

    /**
     * 获取当前数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("Current Datasource is [{}]", DynamicDataSourceContextHolder.getDataSourceKey());
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }

    /**
     * 设置数据源
     * @param targetDataSources
     */
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 获取存储已实例化的数据源map
     * @return
     */
    public Map<Object, Object> getDataSourceMap(){
        return dataSourceMap;
    }

    /**
     * 单例方法
     * @return
     */
    public static synchronized DynamicDataSource getInstance(){
        if(instance == null){
            synchronized (lock){
                if(instance == null){
                    instance = new DynamicDataSource();
                }
            }
        }
        return instance;
    }

    public static boolean isExistDataSource(String key){
        return dataSourceMap.containsKey(key);
    }

}
