package com.wucfu.example.seata.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    Integer reduceCount( @Param("productId") Integer productId,@Param("amount") Integer amount);

    Integer reduceCountByBatch(List<Integer> productIds);

    Integer selectCountById(@Param("productId") Integer productId);
}
