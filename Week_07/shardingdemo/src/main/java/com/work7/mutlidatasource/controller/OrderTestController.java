package com.work7.mutlidatasource.controller;


import com.work7.mutlidatasource.domain.TOrder;
import com.work7.mutlidatasource.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/order")
@RestController
public class OrderTestController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("queryById")
    public TOrder queryById(Long orderId){
        return orderService.queryById(orderId);
    }

    @RequestMapping("save")
    public int saveOrder(){
        TOrder order = new TOrder();
        order.setUserId(1111);
        order.setStatus(1);
        order.setOrderDesc("000");
        order.setOrderNum("1");
        order.setAddressId(1);
        return orderService.insert(order);
    }

}
