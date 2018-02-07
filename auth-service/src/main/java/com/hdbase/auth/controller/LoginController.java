package com.hdbase.auth.controller;



import com.hdbase.auth.bean.HttpResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Api(value = "/",description = "登录")
public class LoginController {
//
//    @Autowired
//    protected AccountService accountService;
	
	/**
	 * Go login
	 * @param
	 * @return
	 */
	@RequestMapping(value="login", method= RequestMethod.POST)
//	@ApiOperation(value = "登录接口", notes = "登录接口 account，password必填")
	public HttpResult login(@RequestParam String account, @RequestParam String password) {
		System.out.println("account:"+account+" "+"password:"+password);
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




}
