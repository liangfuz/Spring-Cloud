package cn.dingshen.common.utils.weixin;


import cn.dingshen.common.config.WeiXinConfig;

/**
 * 
 * @author zhangguan
 */
public class WeiXinConstants {
	 //用户关注状态
    public final static int ATTENTION_YES=0;//关注用户
    public final static int ATTENTION_NO=1;//未关注用户
    
    //MsgType start
    public static final String MsgType_TEXT="text";
    public static final String MsgType_NEWS="news";
    public static final String MsgType_EVENT="event";
    //MsgType end
    
    
    //Event start
    public static final String Event_CLICK="CLICK";
    public static final String Event_VIEW="VIEW";
    public static final String Event_SUBSCRIBE="subscribe";
    public static final String Event_UNSUBSCRIBE="unsubscribe";
    public static final String Event_SCAN="SCAN";
    
    //Event end
   
    //result start
    public static final String RESULT_TEXT="text";
    public static final String RESULT_NEWS="news";
    public static final String RESULT_ECHOSTR="echostr";
    public static final String RESULT_NOTHING="nothing";
    //result end
    
    //定义被动消息回复类型 start
    public static final String REPLY_TEXT="text";//文本消息
    public static final String REPLY_IMAGE="image";//图片消息
    public static final String REPLY_VOICE="voice";//语音消息
    public static final String REPLY_VIDEO="video";//视频消息
    public static final String REPLY_MUSIC="music";//音乐消息
    public static final String REPLY_NEWS="news";//图文消息
    //定义被动消息回复类型 end
    public final static  String MENU_CREATE="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    public final static  String GET_TOKEN="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    public final static String GET_USER="https://api.weixin.qq.com/cgi-bin/user/info?access_token={access_token}&openid={openid}&lang=zh_CN";
    public final static String QRCODE_CREATE="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
    public final static String GET_OAUTH2="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WeiXinConfig.getInstance().getAppId()+"&redirect_uri={uri}&response_type=code&scope=snsapi_base&state={state}#wechat_redirect";
    public final static String GET_OPENID="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ WeiXinConfig.getInstance().getAppId()+"&secret="+WeiXinConfig.getInstance().getAppSecret()+"&code={code}&grant_type=authorization_code";
    public final static String GET_OAUTH2_USER="https://api.weixin.qq.com/sns/userinfo?access_token={access_token}&openid={openid}&lang=zh_CN";  
    
    public final static String US_OAUTH2="Oauth2.action";
    public final static String GET_JSAPI="https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";
    public final static String GET_IMG="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=";
    public final static String GET_OAUTH2_USERINFO="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WeiXinConfig.getInstance().getAppId()+"&redirect_uri={uri}&response_type=code&scope=snsapi_userinfo&state={state}#wechat_redirect";
    public final static String PUT_MEWS="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";


    //获取所有关注用户资料
    public final static String GET_ALL_USER ="https://api.weixin.qq.com/cgi-bin/user/get?access_token=";

    //关键字回复功能类型
    public final static int KEYWORD_REPLY_BASIC =0;//基本类型
    public final static int KEYWORD_REPLY_NEWS = 1;//多图文消息类型
    public final static int KEYWORD_REPLY_DEFAULT=2;//默认关键字
       
	//微信退款接口
	public static final String refundUrl="https://api.mch.weixin.qq.com/secapi/pay/refund";
	//微信退款查询接口
	public static final String refundQueryUrl="https://api.mch.weixin.qq.com/pay/refundquery";
	
	public static final String genOrderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	public static final String queryOrder = "https://api.mch.weixin.qq.com/pay/orderquery?";

    //微信素材类型
    public static final Integer WEIXIN_MATERIAL_TYPE_TEXT= 1; //文字素材
    public static final Integer WEIXIN_MATERIAL_TYPE_NEWS= 2; //图文素材
    public static final Integer WEIXIN_MATERIAL_TYPE_IMAGE= 3; //图片素材
}
