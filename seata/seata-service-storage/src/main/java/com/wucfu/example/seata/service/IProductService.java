package com.wucfu.example.seata.service;



public interface IProductService {

    boolean reduceCount(Integer amount, Integer productId) throws Exception;
}
