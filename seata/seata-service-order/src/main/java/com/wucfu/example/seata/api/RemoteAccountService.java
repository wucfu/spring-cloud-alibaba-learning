package com.wucfu.example.seata.api;

import com.wucfu.example.seata.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "nacos-seata-account-server")
public interface RemoteAccountService {

    /**
     * 扣减账户余额
     */
    @PostMapping("/account/reduceBalance")
    ResultVo reduceBalance(@RequestParam("userId") Integer userId, @RequestParam("money") BigDecimal money);
}
