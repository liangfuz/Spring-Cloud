package cn.hdbase.common.utils.alipay;

import cn.hdbase.common.config.AlipayConfig;
import cn.hdbase.common.config.SystemConfig;

/**
 * 支付宝常量类
 *
 * @author jiangrujie
 * @create 2017-07-03 上午 9:47
 **/
public class AlipayConstants {
    //支付状态
    public final static String TRADE_STATUS_WAIT_PAY="WAIT_BUYER_PAY"; //交易创建，等待买家付款
    public final static String TRADE_STATUS_CLOSED="TRADE_CLOSED"; //未付款交易超时关闭，或支付完成后全额退款
    public final static String TRADE_STATUS_SUCCESS="TRADE_SUCCESS"; //支付成功
    public final static String TRADE_STATUS_FINISHED="TRADE_FINISHED"; //交易结束，不可退款

    public final static String OAUTH2_URL = AlipayConfig.getInstance().getAuthUrl()+"/oauth2/publicAppAuthorize.htm?app_id="+AlipayConfig.getInstance().getAppId()+"&scope={scope}&redirect_uri={uri}&state={state}";
    public final static String GATEWAY_URL = AlipayConfig.getInstance().getApiUrl()+"/gateway.do";
    //前端回跳地址
    public final static String RETURN_URL = SystemConfig.getInstance().getDomain() + SystemConfig.getInstance().getMobileProject() + "/views/order/alipay.html";
    //后台通知地址
    public final static String NOTIFY_WAP_URL = SystemConfig.getInstance().getDomain() + SystemConfig.getInstance().getBackendProject() + "/alipay/payment/asyncAliNotify";
    public final static String NOTIFY_SCAN_CODE_URL = SystemConfig.getInstance().getDomain() + SystemConfig.getInstance().getBackendProject() + "/alipay/payment/asyncNotifyForScanCode";
}
