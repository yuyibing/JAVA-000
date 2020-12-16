package com.work7.mutlidatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.work7.mutlidatasource.datasource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@MapperScan(basePackages  = DBConfig.PACKAGE , sqlSessionFactoryRef = "sqlSessionFactory")
public class DBConfig {
//    private Logger logger = LoggerFactory.getLogger(DBConfig.class);
    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.work7.**.mapper";
    private static final String MAPPER_LOCATION = "classpath*:com/work7/mutlidatasource/mapper/*.xml";
    private static final String DOMAIN_PACKAGE = "com.work7.**.domain";

    @Autowired
    private Environment evn;

    private DruidDataSource createDataSource(String type){
        String dbName = type.trim().isEmpty() ? "default" : type.trim();
        DruidDataSource dataSource = new DruidDataSource();
        String prefix = "datasource." + dbName + ".";
        dataSource.setUrl(evn.getProperty(prefix + "url"));
        dataSource.setUsername(evn.getProperty(prefix + "username"));
        dataSource.setPassword(evn.getProperty(prefix + "password"));
        dataSource.setDriverClassName(evn.getProperty(prefix + "driverClassName"));
        return dataSource;
    }

    @Bean
    public DynamicDataSource dynamicDataSource(){
        // 获取动态数据源的实例
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        // 创建默认数据库连接对象
        DruidDataSource defaultDataSource = createDataSource("master");
        // 创建从数据库连接对象
        DruidDataSource slaveDataSource1 = createDataSource("slave1");
        DruidDataSource slaveDataSource2 = createDataSource("slave2");

        Map<Object, Object> map = new HashMap<>();
        map.put("default", defaultDataSource);
        map.put("slave1", slaveDataSource1);
        map.put("slave2", slaveDataSource1);
        dynamicDataSource.setTargetDataSources(map);
        // 设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);

        return dynamicDataSource;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DBConfig.MAPPER_LOCATION));
        sessionFactory.setTypeAliasesPackage(DOMAIN_PACKAGE);
        //mybatis 数据库字段与实体类属性驼峰映射配置
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }
}
