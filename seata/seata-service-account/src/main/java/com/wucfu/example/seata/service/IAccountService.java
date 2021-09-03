package com.wucfu.example.seata.service;


import java.math.BigDecimal;


public interface IAccountService  {

    /**
     * 扣减账户余额
     * @param userId
     * @param balance
     * @return
     */
    boolean reduceBalance(Integer userId, BigDecimal balance) throws Exception;
}
