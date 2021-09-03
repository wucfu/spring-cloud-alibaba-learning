package com.wucfu.example.seata.vo;

import lombok.Data;

@Data
public class ResultVo {
    private boolean success;
    private String msg;
    private Object data;
}
