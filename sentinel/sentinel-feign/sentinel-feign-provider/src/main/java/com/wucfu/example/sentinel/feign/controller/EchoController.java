
package com.wucfu.example.sentinel.feign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

	@GetMapping("/echo/{str}")
	public String echo(@PathVariable String str) {
		return "provider-" + str;
	}

}
