package mysql.jdbc.work.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariUtil {
    private static HikariDataSource hikariDataSource;

    static{
        // 如何获得属性文件的输入流？
        // 通常情况下使用类的加载器的方式进行获取：
        try (InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("hikrai-config.properties")) {
            // 加载属性文件并解析：
            Properties props = new Properties();
            props.load(is);
            HikariConfig config = new HikariConfig(props);
            hikariDataSource = new HikariDataSource(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个数据库链接
     */
    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
