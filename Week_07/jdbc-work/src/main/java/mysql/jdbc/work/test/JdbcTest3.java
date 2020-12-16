package mysql.jdbc.work.test;

import mysql.jdbc.work.utils.HikariUtil;
import mysql.jdbc.work.utils.HikariUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 使用事务，PrepareStatement 方式，批处理方式，改进
 */
public class JdbcTest3 {

    /**
     * 增加数据
     */
    public static void add(int id, String name) throws SQLException{
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "insert into student values (?,?)";
            conn = HikariUtil.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            conn.commit();
        }catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除数据
     */
    public static void delete(String name) throws SQLException{
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE FROM student WHERE student.name = ?";
            conn = HikariUtil.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            conn.commit();
        }catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 修改数据
     */
    public static void update(int id, String name) throws SQLException{
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE student SET student.name = ? where student.id = ?";
            conn = HikariUtil.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            conn.commit();
        }catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 查找数据
     */
    public static void query(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM student where id = ?";
            conn = HikariUtil.getConnection();
            conn.setAutoCommit(false);
            //执行查询语句并返回结果集
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //注意：这里要与数据库里的字段对应
                int index = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println(index + " " + name);
            }
            conn.commit();
        }catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 批量插入
     */
    public static void addBatch() throws SQLException{
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            String sql = "insert into student values(?,?)";
            conn = HikariUtil.getConnection();
            conn.setAutoCommit(false);

            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < 10000; i++) {//100万条数据
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "test");
                preparedStatement.addBatch();
                if(i%100==0){
                   preparedStatement.executeBatch();
                }
             }
            preparedStatement.executeBatch();
            conn.commit();
        }catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws SQLException {
        add(1, "xxx");

//        delete("test");

//        update(1,"zzzz");

//        query(1);

//        addBatch();
    }

}
