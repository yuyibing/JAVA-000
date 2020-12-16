package mysql.jdbc.work.week7;

import mysql.jdbc.work.dao.TOrderMapper;
import mysql.jdbc.work.model.TOrder;
import mysql.jdbc.work.utils.DBUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
 */
public class InsertMillionOrderRecord {

    /**
     * JDBC批量插入
     */
    public static void addBatch() throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "insert into t_order values(?,?,?,?,?,?,?,?,?)";
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < 1000000; i++) {//100万条数据
                preparedStatement.setInt(1, i);
                preparedStatement.setInt(2, 1);
                preparedStatement.setInt(3, 1);
                preparedStatement.setFloat(4, 1.0f);
                preparedStatement.setLong(5, System.currentTimeMillis());
                preparedStatement.setInt(6, 1);
                preparedStatement.setInt(7, 1);
                preparedStatement.setString(8, "");
                preparedStatement.setString(9, "");
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * MyBatis批量插入
     */
    public static void addBatchByMybatis() throws SQLException {
        SqlSession session = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession(ExecutorType.BATCH);

            TOrderMapper orderMapper = session.getMapper(TOrderMapper.class);
            for(int k = 0; k < 100; k++) {
                List<TOrder> orders = new ArrayList<>();
                for (int i = 0; i < 10000; i++) {//100万条数据
                    TOrder order = new TOrder();
                    order.setUserId(1);
                    order.setOrderNum("1");
                    order.setAddressId(1);
                    order.setOrderDesc("");
                    order.setOrderTime(System.currentTimeMillis());
                    order.setOrderPrice(1.0f);
                    order.setStatus(1);
                    order.setUserMark("");
                    orders.add(order);
                }

                orderMapper.insertList(orders);
                session.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }

    public static void main(String[] args) throws SQLException {
        long s1 = System.currentTimeMillis();
        addBatch();
//        addBatchByMybatis();
        long s2 = System.currentTimeMillis();
        System.out.println("插入 100 万订单模拟数据执行时间：" + (s2 - s1) + "ms");

    }

}
