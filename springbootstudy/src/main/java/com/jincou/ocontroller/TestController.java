package com.jincou.ocontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	
	@RequestMapping("/vq/test")
	public  String  getTest(){
		
		return"我是测试返回值";
	}
}
