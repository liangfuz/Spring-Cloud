package com.hdbase.auth.controller;




import cn.hdbase.common.bean.HttpResult;
import com.hdbase.auth.client.ProductClient;
import com.netflix.client.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
//@Api(value = "/",description = "登录")
public class LoginController {

	@Autowired
	ProductClient productClient;
	
	/**
	 * Go login
	 * @param
	 * @return
	 */
	@RequestMapping(value="login")
//	@ApiOperation(value = "登录接口", notes = "登录接口 account，password必填")
	public HttpResult login(@RequestParam String account, @RequestParam String password,HttpServletRequest request) {
		System.out.println("account:"+account+" "+"password:"+password);
		request.getSession().setAttribute("testSession","123");
		UsernamePasswordToken upt = new UsernamePasswordToken(account, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(upt);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return HttpResult.createFAIL("您的账号或密码输入错误!");
		}
		return HttpResult.createSuccess("登录成功!");
	}

	/**
	 * Exit
	 * @return
	 */
//	@ApiOperation(value = "登出接口")
	@RequestMapping(value="logout",method= RequestMethod.POST)
	public HttpResult logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return HttpResult.createSuccess("登出成功!");
	}


	@RequestMapping(value="/product/get")
	public HttpResult productGet(HttpServletRequest request) {

		String name=productClient.get("1234");
//		request.getSession().getAttribute("testSession");
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		log.info("sessionId:"+sessionId);
		return  HttpResult.createSuccess(name);
	}




}
