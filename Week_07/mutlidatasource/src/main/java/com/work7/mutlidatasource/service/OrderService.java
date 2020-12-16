package com.work7.mutlidatasource.service;

import com.work7.mutlidatasource.domain.TOrder;

public interface OrderService {

    public int insert(TOrder order);

    public TOrder queryById(Long id);

}
