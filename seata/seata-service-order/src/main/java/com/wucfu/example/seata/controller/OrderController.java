package com.wucfu.example.seata.controller;


import com.wucfu.example.seata.domin.Order;
import com.wucfu.example.seata.service.IOrderService;
import com.wucfu.example.seata.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/create")
    public ResultVo create(Order order) {

        ResultVo resultVo = new ResultVo();
        try {
            orderService.createOrder(order);
            resultVo.setMsg("创建订单成功");
            resultVo.setSuccess(true);
            resultVo.setData("订单ID:"+order.getId());

        } catch (Exception e) {
            resultVo.setMsg("创建订单失败");
            resultVo.setSuccess(false);
            e.printStackTrace();
        }
        return resultVo;
    }
}

