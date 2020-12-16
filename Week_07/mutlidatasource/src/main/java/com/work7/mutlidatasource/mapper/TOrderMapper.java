package com.work7.mutlidatasource.mapper;

import com.work7.mutlidatasource.annotation.ReadOnly;
import com.work7.mutlidatasource.domain.TOrder;
import com.work7.mutlidatasource.domain.TOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TOrderMapper {
    long countByExample(TOrderExample example);

    int deleteByExample(TOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    @ReadOnly
    List<TOrder> selectByExample(TOrderExample example);

    @ReadOnly
    TOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TOrder record, @Param("example") TOrderExample example);

    int updateByExample(@Param("record") TOrder record, @Param("example") TOrderExample example);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);

    int insertList(@Param("records") List<TOrder> records);
}