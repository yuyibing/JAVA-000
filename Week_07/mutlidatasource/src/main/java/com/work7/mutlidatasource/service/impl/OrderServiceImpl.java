package com.work7.mutlidatasource.service.impl;

import com.work7.mutlidatasource.annotation.ReadOnly;
import com.work7.mutlidatasource.domain.TOrder;
import com.work7.mutlidatasource.mapper.TOrderMapper;
import com.work7.mutlidatasource.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TOrderMapper orderMapper;

    @Override
    public int insert(TOrder order) {
        return orderMapper.insert(order);
    }

    @Override
    public TOrder queryById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }
}
