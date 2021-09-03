package com.wucfu.example.seata.service;

import com.wucfu.example.seata.api.RemoteAccountService;
import com.wucfu.example.seata.api.RemoteStorageService;
import com.wucfu.example.seata.domin.Order;
import com.wucfu.example.seata.mapper.OrderMapper;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalLock;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private RemoteStorageService remoteStorageService;

    @Autowired
    private RemoteAccountService remoteAccountService;

    @Autowired
    private OrderMapper orderMapper;

    @GlobalTransactional(name = "prex-create-order", rollbackFor = Exception.class)
    //@Transactional
    @Override
    public void createOrder(Order order) {
        log.info("当前 XID: {}", RootContext.getXID());
        log.info("下单开始,用户:{},商品:{},数量:{},金额:{}", order.getUserId(), order.getProductId(), order.getCount(), order.getPayMoney());
        //创建订单
        order.setStatus(0);
        orderMapper.saveOrder(order);
        log.info("保存订单{}", order);

        //远程调用库存服务扣减库存
        log.info("扣减库存开始");
        remoteStorageService.reduceCount(order.getProductId(), order.getCount());
        log.info("扣减库存结束");

        //远程调用账户服务扣减余额
        log.info("扣减余额开始");
        remoteAccountService.reduceBalance(order.getUserId(), order.getPayMoney());
        log.info("扣减余额结束");

        //System.out.println(1/0);
        //修改订单状态为已完成
        log.info("修改订单状态开始");
        orderMapper.updateOrderStatusById(order.getId(), 1);
        log.info("修改订单状态结束");

        log.info("下单结束");
    }
}
