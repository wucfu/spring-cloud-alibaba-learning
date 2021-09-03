package com.wucfu.example.seata.mapper;

import com.wucfu.example.seata.domin.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

    int saveOrder(Order order);

    int updateOrderStatusById(@Param("orderId") Integer orderId,@Param("status") Integer orderStatus);
}
