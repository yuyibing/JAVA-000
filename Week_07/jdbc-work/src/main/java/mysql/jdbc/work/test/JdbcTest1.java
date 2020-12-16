package mysql.jdbc.work.test;

import mysql.jdbc.work.utils.DBUtil;

import java.sql.*;

/**
 * 使用 JDBC 原生接口，实现数据库的增删改查操作
 */
public class JdbcTest1 {

    /**
     * 创建表
     */
    public static void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS `student`("
                + "`id` INT UNSIGNED primary key,"
                + " `name` VARCHAR(100)"
                + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        Connection conn = DBUtil.getConnection();
        //开启事务
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        //提交事务
        conn.commit();
        conn.close();
    }

    /**
     * 增加数据
     */
    public static void add() throws SQLException{
        String sql = "INSERT INTO student(id,name) VALUES(1, 'yyyy')";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        Statement statement = conn.createStatement();
        statement.execute(sql);
        conn.commit();
        conn.close();
    }

    /**
     * 删除数据
     */
    public static void delete() throws SQLException{
        String sql = "DELETE FROM student WHERE student.name = 'xxxx'";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        Statement statement = conn.createStatement();
        statement.execute(sql);
        conn.commit();
        conn.close();
    }

    /**
     * 修改数据
     */
    public static void update() throws SQLException{
        String sql = "UPDATE student SET student.name = 'xxxx'";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        Statement statement = conn.createStatement();
        statement.execute(sql);
        conn.commit();
        conn.close();
    }

    /**
     * 查找数据
     */
    public static void query() throws SQLException{
        String sql = "SELECT * FROM student";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        //执行查询语句并返回结果集
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            //注意：这里要与数据库里的字段对应
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println(id + " " + name);
        }
        conn.commit();
        conn.close();
    }


    public static void main(String[] args) throws SQLException {
//        createTable();

//        add();
//
        delete();
//
//        update();
//
//        query();
    }

}
