package com.wucfu.example.sentinel.feign.controller;

import com.wucfu.example.sentinel.feign.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wucfu
 * @description
 * @date 2020-09-30
 */
@RestController
public class TestController {

    @Autowired
    private EchoService echoService;

    @GetMapping("/echo-feign/{str}")
    public String feign(@PathVariable String str) {
        return echoService.echo(str);
    }

}