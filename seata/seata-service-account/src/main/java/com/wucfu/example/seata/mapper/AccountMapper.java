package com.wucfu.example.seata.mapper;


import com.wucfu.example.seata.domin.Account;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


public interface AccountMapper {

    /**
     * 扣减账户余额
     * @param userId
     * @param balance
     * @return
     */
    Integer reduceBalance(@Param("userId") Integer userId, @Param("balance") BigDecimal balance);

    Account selectByUserId(@Param("userId") Integer userId);
}
