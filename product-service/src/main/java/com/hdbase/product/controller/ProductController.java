package com.hdbase.product.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制类
 * 
 * @author jiangrj
 *
 */
@RefreshScope
@RestController
@RequestMapping("/product")
public class ProductController {

	@Value("${members}")
	private String members;


	@ApiOperation(value="获取商品详细信息", notes="根据商品名来获取商品详细信息")
	@ApiImplicitParam(name = "name", value = "商品名", required = true, dataType = "String")
	@RequestMapping(value="/get", method= RequestMethod.GET)
	public String get(@RequestParam String name) {
		return "hello "+name+"，this is product info ";
	}

	@ApiOperation(value="获取用户列表", notes="")
	@RequestMapping("/listMembers")
	public String listMembers() {
		return "hello,our team members: "+members;
	}

}
