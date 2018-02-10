package com.hdbase.product.controller;

import cn.hdbase.common.shiro.ShiroUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单控制类
 * 
 * @author jiangrj
 *
 */
@RefreshScope
@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Value("${members}")
	private String members;


	@ApiOperation(value="获取商品详细信息", notes="根据商品名来获取商品详细信息")
	@ApiImplicitParam(name = "name", value = "商品名", required = true, dataType = "String")
	@RequestMapping(value="/get", method= RequestMethod.GET)
	public String get(@RequestParam String name,HttpServletRequest request) {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		log.info("user_name:"+user.getAccount());
		String test=(String)request.getSession().getAttribute("testSession");
		log.info("test:"+test);
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		log.info("sessionId:"+sessionId);
		return "hello "+name+"，this is product info ";
	}

	@ApiOperation(value="获取用户列表", notes="")
	@RequestMapping("/listMembers")
	public String listMembers() {
		return "hello,our team members: "+members;
	}

	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public ResponseEntity<String> hello(){
		logger.info("called by product-service");
		return new ResponseEntity<String>("hello product service!", HttpStatus.OK);
	}

}
