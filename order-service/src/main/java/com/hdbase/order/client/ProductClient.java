package com.hdbase.order.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品服务调用客户端
 *
 * @author jiangrujie
 * @create 2018-01-25 上午 11:13
 **/
@FeignClient(name= "product-service") //配置文件中注册到注册中心的名字
public interface ProductClient {
    @RequestMapping(value = "/product/get", method= RequestMethod.GET) //方法和ProductController一致
    String get(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/product/listMembers") //方法和ProductController一致
    String listMembers();
}
