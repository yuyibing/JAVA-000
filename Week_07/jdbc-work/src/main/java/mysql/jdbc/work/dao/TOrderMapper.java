package mysql.jdbc.work.dao;

import mysql.jdbc.work.model.TOrder;
import mysql.jdbc.work.model.TOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TOrderMapper {
    long countByExample(TOrderExample example);

    int deleteByExample(TOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    List<TOrder> selectByExample(TOrderExample example);

    TOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TOrder record, @Param("example") TOrderExample example);

    int updateByExample(@Param("record") TOrder record, @Param("example") TOrderExample example);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);

    int insertList(@Param("records") List<TOrder> records);
}