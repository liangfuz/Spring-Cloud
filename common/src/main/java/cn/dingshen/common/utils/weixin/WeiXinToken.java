package cn.dingshen.common.utils.weixin;

import cn.dingshen.common.config.SpringApplicationContextHolder;
import cn.dingshen.common.config.WeiXinConfig;
import cn.dingshen.common.utils.HttpHandler;
import cn.dingshen.common.utils.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Slf4j
public class WeiXinToken {
    
    private static String jsapiTicket;
    
    private static long jsapiTime=0;
    
    public static String freshToken(){
        String url= WeiXinConstants.GET_TOKEN+"&appid="+WeiXinConfig.getInstance().getAppId()+"&secret="+ WeiXinConfig.getInstance().getAppSecret();
        String result=null;
        try {
            result = HttpHandler.doGet(url);
        } catch (Exception e) {
            log.error("刷新Token失败:"+ e.getMessage(), e);
        }
        JSONObject jso=JSONObject.fromObject(result);
        String accessToken = "";
        if(jso.has("access_token")){
            accessToken=jso.getString("access_token");
        }
        return accessToken;
    }
    //获取token
    public  static String getToken(){
        TokenService tokenService = (TokenService) SpringApplicationContextHolder.getSpringBean("tokenService");
        return tokenService.getWxToken();
    }
    
    public  static String getJsapiTicket(){
        if(System.currentTimeMillis()-jsapiTime>110*60*1000){
            freshJsapiTicket();
        }
        return jsapiTicket;
    }
    //刷新token
    public static void freshJsapiTicket(){
        String url= WeiXinConstants.GET_JSAPI+getToken();
        String reuslt=null;
        try {
            reuslt = HttpHandler.doGet(url);
        } catch (Exception e) {
            log.error("刷新JsapiTicket失败:"+ e.getMessage(), e);
        }
        JSONObject jso=JSONObject.fromObject(reuslt);
        if(jso.has("ticket")){
            jsapiTicket=jso.getString("ticket");
            jsapiTime=System.currentTimeMillis();
        }
    }
    
}
