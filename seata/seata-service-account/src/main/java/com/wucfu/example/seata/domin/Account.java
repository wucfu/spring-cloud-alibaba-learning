package com.wucfu.example.seata.domin;

import lombok.Data;


import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class Account implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 余额
     */
    private BigDecimal balance;


}
