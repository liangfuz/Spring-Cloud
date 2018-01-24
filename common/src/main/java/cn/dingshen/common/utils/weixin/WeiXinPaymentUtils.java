package cn.dingshen.common.utils.weixin;

import cn.dingshen.common.config.WeiXinConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.Reader;
import java.io.StringReader;
import java.util.*;

@Slf4j
public class WeiXinPaymentUtils {
    //生成预付订单xml
    public static String generateWeiXinPreOrderXml(String openId,Integer totalFee,
    		String orderNum,String spbillCreateIp,String body,String notify_url){
    	 String nonceStr = RandomStringUtils.random(8, true, true);  //随机字符串
    	 Map<String, String> paramMap = new HashMap<String, String>();
    	 paramMap.put("appid", WeiXinConfig.getInstance().getAppId());//设置appid
    	 paramMap.put("mch_id", WeiXinConfig.getInstance().getMchId());//设置商户id
         paramMap.put("nonce_str", nonceStr);//随机字符串
         paramMap.put("out_trade_no", orderNum);//订单号
         paramMap.put("notify_url", notify_url);
         paramMap.put("body", body);
         paramMap.put("spbill_create_ip", spbillCreateIp);
         paramMap.put("openid", openId);
         paramMap.put("total_fee", totalFee.toString());
         paramMap.put("trade_type", "JSAPI");
         String sign = getMd5(paramMap);//加密生成sign
         return "<xml>"
         		+ "<appid>"+WeiXinConfig.getInstance().getAppId()+"</appid>"
         		+ "<body>"+body+"</body>"
         		+ "<mch_id>"+WeiXinConfig.getInstance().getMchId()+"</mch_id>"
         		+ "<nonce_str>"+nonceStr+"</nonce_str>"
         		+ "<notify_url><![CDATA["+notify_url+"]]></notify_url>"
         		+ "<openid>"+openId+"</openid>"
         		+ "<out_trade_no>"+orderNum+"</out_trade_no>"
         		+ "<spbill_create_ip>"+spbillCreateIp+"</spbill_create_ip>"
         		+ "<total_fee>"+totalFee.toString()+"</total_fee>"
         		+ "<trade_type>JSAPI</trade_type>"
         		+ "<sign>"+sign+"</sign>"
         		+ "</xml>";
    }

    /**
     * 微信扫码支付
     * @param totalFee 订单总金额，单位为分
     * @param orderNum 订单号
     * @param spbillCreateIp 微信支付API的机器IP
     * @param body 商品描述
     * @param notifyUrl 微信支付结果通知的回调地址
     * @return
     */
    public static String generateWeiXinPreOrderXmlForNative(Integer totalFee,
    		String orderNum,String spbillCreateIp,String body,String notifyUrl){
    	 String nonceStr = RandomStringUtils.random(8, true, true);  //随机字符串
    	 Map<String, String> paramMap = new HashMap<String, String>();
    	 paramMap.put("appid", WeiXinConfig.getInstance().getAppId());//设置appid
    	 paramMap.put("mch_id", WeiXinConfig.getInstance().getMchId());//设置商户id
         paramMap.put("nonce_str", nonceStr);//随机字符串
         paramMap.put("out_trade_no", orderNum);//订单号
         paramMap.put("notify_url", notifyUrl);
         paramMap.put("body", body);
         paramMap.put("spbill_create_ip", spbillCreateIp);
         paramMap.put("total_fee", totalFee.toString());
         paramMap.put("trade_type", "NATIVE");
         String sign = getMd5(paramMap);//加密生成sign
         return "<xml>"
         		+ "<appid>"+WeiXinConfig.getInstance().getAppId()+"</appid>"
         		+ "<body>"+body+"</body>"
         		+ "<mch_id>"+WeiXinConfig.getInstance().getMchId()+"</mch_id>"
         		+ "<nonce_str>"+nonceStr+"</nonce_str>"
         		+ "<notify_url><![CDATA["+notifyUrl+"]]></notify_url>"
         		+ "<out_trade_no>"+orderNum+"</out_trade_no>"
         		+ "<spbill_create_ip>"+spbillCreateIp+"</spbill_create_ip>"
         		+ "<total_fee>"+totalFee.toString()+"</total_fee>"
         		+ "<trade_type>NATIVE</trade_type>"
         		+ "<sign>"+sign+"</sign>"
         		+ "</xml>";
    }

    public static String generateWeiXinQueryXml(String weixinOrderNum){
        String nonceStr = RandomStringUtils.random(8, true, true);        
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", WeiXinConfig.getInstance().getAppId());
        paramMap.put("mch_id", WeiXinConfig.getInstance().getMchId());
        paramMap.put("nonce_str", nonceStr);
        paramMap.put("transaction_id", weixinOrderNum);

        String sign = getMd5(paramMap);
            
        return "<xml>"
            + "<appid>" + WeiXinConfig.getInstance().getAppId() + "</appid>"
            + "<mch_id>" + WeiXinConfig.getInstance().getMchId() + "</mch_id>"
            + "<nonce_str>" + nonceStr + "</nonce_str>" 
            + "<transaction_id>" + weixinOrderNum + "</transaction_id>" 
            + "<sign>" + sign + "</sign>" 
            + "</xml>";
    }
    /**
     * 生成退款xml
     * 公众号id  appid
     * 商户号   mch_id
     * 随机字符串  nonce_str
     * 签名 sign
     * 微信订单号  transaction_id
     * 商户订单号 out_trade_no
     * 商户退款单号  out_refund_no
     * 总金额  total_fee
     * 退款金额  refund_fee
     * 商户操作员(默认为商户号) op_user_id
     * @return
     */
//    public static String generateWeiXinRefundXml(String orderNum,String otherOrderNum,String refundNo,
//    		Integer totalFee,Integer refundFee){
//    	String nonceStr = RandomStringUtils.random(8, true, true);  //随机字符串
//    	Map<String, String> paramMap = new HashMap<String, String>();
//    	paramMap.put("appid", WeiXinConfig.getInstance().getAppId());//设置appid
//    	paramMap.put("mch_id", WeiXinConfig.getInstance().getMchId());//设置商户id
//    	paramMap.put("nonce_str", nonceStr);//随机字符串
//    	paramMap.put("out_trade_no", orderNum);//订单号
//    	paramMap.put("transaction_id", otherOrderNum);//订单号
//    	paramMap.put("out_refund_no", refundNo);//退款单号
//    	paramMap.put("total_fee", totalFee.toString());//订单总金额
//    	paramMap.put("refund_fee", refundFee.toString());//退款金额
//    	paramMap.put("op_user_id", WeiXinConfig.getInstance().getMchId());//退款金额
//    	String sign = getMd5(paramMap);//加密生成sign
//    	return "<xml>"
//	 		+ "<appid>"+WeiXinConfig.getInstance().getAppId()+"</appid>"
//	 		+ "<mch_id>"+WeiXinConfig.getInstance().getMchId()+"</mch_id>"
//	 		+ "<nonce_str>"+nonceStr+"</nonce_str>"
//	 		+ "<op_user_id>"+WeiXinConfig.getInstance().getMchId()+"</op_user_id>"
//	 		+ "<out_refund_no>"+refundNo+"</out_refund_no>"
//	 		+ "<out_trade_no>"+orderNum+"</out_trade_no>"
//	 		+ "<refund_fee>"+refundFee+"</refund_fee>"
//	 		+ "<total_fee>"+totalFee.toString()+"</total_fee>"
//	 		+ "<transaction_id>"+otherOrderNum+"</transaction_id>"
//	 		+ "<sign>"+sign+"</sign>"
//	 		+ "</xml>";
//    }

    public static String generateWeiXinRefundXml(String orderNum, String refundNo, Integer totalFee, Integer refundFee){
        String nonceStr = RandomStringUtils.random(8, true, true);  //随机字符串
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", WeiXinConfig.getInstance().getAppId());//设置appid
        paramMap.put("mch_id", WeiXinConfig.getInstance().getMchId());//设置商户id
        paramMap.put("nonce_str", nonceStr);//随机字符串
        paramMap.put("out_trade_no", orderNum);//订单号
        paramMap.put("out_refund_no", refundNo);//退款单号
        paramMap.put("total_fee", totalFee.toString());//订单总金额
        paramMap.put("refund_fee", refundFee.toString());//退款金额
        paramMap.put("op_user_id", WeiXinConfig.getInstance().getMchId());//退款金额
        String sign = getMd5(paramMap);//加密生成sign
        return "<xml>"
                + "<appid>"+WeiXinConfig.getInstance().getAppId()+"</appid>"
                + "<mch_id>"+WeiXinConfig.getInstance().getMchId()+"</mch_id>"
                + "<nonce_str>"+nonceStr+"</nonce_str>"
                + "<op_user_id>"+WeiXinConfig.getInstance().getMchId()+"</op_user_id>"
                + "<out_refund_no>"+refundNo+"</out_refund_no>"
                + "<out_trade_no>"+orderNum+"</out_trade_no>"
                + "<refund_fee>"+refundFee+"</refund_fee>"
                + "<total_fee>"+totalFee.toString()+"</total_fee>"
                + "<sign>"+sign+"</sign>"
                + "</xml>";
    }

    /**
     * 生成退款查询xml
     * 公众号id  appid
     * 商户号   mch_id
     * 随机字符串  nonce_str
     * 签名 sign
     * 微信订单号  transaction_id
     * 商户订单号 out_trade_no
     * 商户退款单号  out_refund_no
     * @return
     */
    public static String generateWeiXinRefundQueryXml(String orderNum,String otherOrderNum,String refundNo,String outRefundNo){
    	String nonceStr = RandomStringUtils.random(8, true, true);  //随机字符串
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("appid", WeiXinConfig.getInstance().getAppId());//设置appid
    	paramMap.put("mch_id", WeiXinConfig.getInstance().getMchId());//设置商户id
    	paramMap.put("nonce_str", nonceStr);//随机字符串
    	paramMap.put("out_trade_no", orderNum);//订单号
    	paramMap.put("transaction_id", otherOrderNum);//订单号
    	paramMap.put("out_refund_no", refundNo);//退款单号
    	if(outRefundNo == null){
    		outRefundNo="";
    	}
    	paramMap.put("refund_id", outRefundNo);//微信退款单号
    	String sign = getMd5(paramMap);//加密生成sign
    	return "<xml>"
	 		+ "<appid>"+WeiXinConfig.getInstance().getAppId()+"</appid>"
	 		+ "<mch_id>"+WeiXinConfig.getInstance().getMchId()+"</mch_id>"
	 		+ "<nonce_str>"+nonceStr+"</nonce_str>"
	 		+ "<out_refund_no>"+refundNo+"</out_refund_no>"
	 		+ "<out_trade_no>"+orderNum+"</out_trade_no>"
	 		+ "<refund_id>"+outRefundNo+"</refund_id>"
	 		+ "<transaction_id>"+otherOrderNum+"</transaction_id>"
	 		+ "<sign>"+sign+"</sign>"
	 		+ "</xml>";
    }
    /**
     * 解析mxl文件 返回map<String,String>
     * @param reader
     * @return
     * @throws Exception
     */
    public static Map<String, String> getXmlData(Reader reader)
            {
    	 Map<String, String> result = new HashMap<String, String>();
    	try{
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(reader);
        Element root = document.getRootElement();
        @SuppressWarnings("unchecked")
		List<Element> elements=root.elements();
        for(Element temp:elements){
        	  result.put(temp.getName(), temp.getStringValue());
        }
        reader.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        log.info("getXmlData:" + result);
        return result;
    }

    public static Map<String, String> getXmlData(String str) throws Exception {
        StringReader strReader = new StringReader(str);
        return getXmlData(strReader);
    }
    /**
     * 用MD5方式对进行签名加密
     * @param paramMap
     * @return
     */
    public static String getMd5(Map<String, String> paramMap) {
    	//加密
        String reqStr = createLinkString(paramMap);
        //添加key值
        String genMd5Str = reqStr + "&key=" + WeiXinConfig.getInstance().getKey();
        //转化成大写
        try {
            log.info("getMd5,genMd5Str:" + genMd5Str);
            return DigestUtils.md5Hex(genMd5Str).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 验证返回签名是否正确
     * @param map
     * @return
     */
    public static boolean validateCallbackData(Map<String, String> map) {
        String md5 = getMd5(map);
        String sign = map.get("sign");
        boolean result = md5.equals(sign);
         log.info("md5, sign, result:" + md5 + "," + sign + "," + result);
        return result;
    }

//    private static String returnSignData(String url,
//            Map<String, String> paramMap) {
//        String reqStr = createLinkString(paramMap);
//        String genMd5Str = reqStr + "&key=" + WeiXinConfig.Key;
//        String md5 = DigestUtils.md5Hex(genMd5Str).toUpperCase();
//        return url + reqStr + "&sign=" + md5;
//    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * 
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (key.equals("sign")) {
                log.info("sign key continue...");
                continue;
            }
            if(!StringUtils.isEmpty(value)){
            prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr.substring(0, prestr.length() - 1);
    }

    
}
