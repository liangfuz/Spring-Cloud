package com.hdbase.order.controller;

import com.hdbase.order.client.ProductClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	ProductClient productClient;

	@RequestMapping("/create/{name}")
	public String create(@PathVariable("name") String name) {
		logger.info("order-service calling product-service!");
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
