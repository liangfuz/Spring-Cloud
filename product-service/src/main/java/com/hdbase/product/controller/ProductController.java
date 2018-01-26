package com.hdbase.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制类
 * 
 * @author guooo
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	@Value("${members}")
	private String members;

	@RequestMapping("/get")
	public String get(@RequestParam String name) {
		return "hello "+name+"，this is product info ";
	}

	@RequestMapping("/listMembers")
	public String listMembers() {
		return "hello,our team members: "+members;
	}

}
