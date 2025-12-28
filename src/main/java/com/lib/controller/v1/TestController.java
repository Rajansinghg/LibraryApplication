package com.lib.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/home")
public class TestController {
	@GetMapping
	public String getMethodName() {
		return "library home controller is working fine";
	}

}
