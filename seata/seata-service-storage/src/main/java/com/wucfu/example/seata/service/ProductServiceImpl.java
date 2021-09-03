package com.wucfu.example.seata.service;


import com.wucfu.example.seata.mapper.ProductMapper;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalLock;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean reduceCount(Integer productId, Integer amount) throws Exception {
        log.info("商品Id:{},商品数量:{}",productId,amount);
        log.info("当前 XID: {}", RootContext.getXID());
        // 检查库存
        checkStock(productId, amount);
        log.info("开始扣减 {} 库存", productId);
        Integer record = productMapper.reduceCount(productId,amount);

        log.info("结束扣减 {} 库存结果:{}", productId, record > 0 ? "操作成功" : "扣减库存失败");
        return record > 0;
    }





    private void checkStock(Integer productId, Integer requiredAmount) throws Exception {
        log.info("检查 {} 库存", productId);
        int countInDb = productMapper.selectCountById(productId);
        log.info("数据库库存:{}",countInDb);
        if (countInDb < requiredAmount) {
            log.warn("{} 库存不足，当前库存:{}", productId, countInDb);
            throw new Exception("库存不足");
        }
    }
}
