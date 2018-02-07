package com.hdbase.order.controller;

import com.hdbase.order.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制类
 * 
 * @author guooo
 *
 */
@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderController {
	@Value("${orderVersion}")
	private String orderVersion;

	@Autowired
	ProductClient productClient;

	@RequestMapping("/create/{name}")
	public String create(@PathVariable("name") String name) {
		System.out.println("create order!");
		//调用商品服务
		return productClient.get(name);
	}

	@RequestMapping("/listMembers")
	public String listMembers() {
		return productClient.listMembers();
	}

	@RequestMapping("/listVersion")
	public String listVersion() {
		return orderVersion;
	}

}
