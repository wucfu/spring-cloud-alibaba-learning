package com.wucfu.example.seata.service;


import com.wucfu.example.seata.domin.Order;

public interface IOrderService {

    void createOrder(Order order);
}
