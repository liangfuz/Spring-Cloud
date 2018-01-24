package cn.dingshen.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/14.
 */
public class Contants {
    public static final int USER_TYPE_SUPER_SYS=0;
    public static final int USER_TYPE_NOMAL_SYS=1;
    public static final String IMG_FOLDER_BRAND="brand";
    public static final String IMG_FOLDER_RS="restaurant";
    public static final String IMG_FOLDER_DISH="dish";
    public static final String BIG_IMG_FOLDER_DISH="dishbigimage";
    public static final String IMG_FOLDER_RS_CATEGORY ="rscategoryimage";
    public static final String IMG_FOLDER_DISH_PLATE ="platedishimage";
    public static final String IMG_FOLDER_AD="ad";
    public static final String IMG_FOLDER_CP="coupon";
    public static final String IMG_FOLDER_WEIXIN="weixin";
    public static final String IMG_FOLDER_TEMP="temp";
    public static final String App_FOLDER="app";

    public static final Integer DiSh_ORDER_STATE_NEW=1;
    public static final Integer DiSh_ORDER_STATE_WAIT_PAY=2;
    public static final Integer DiSh_ORDER_STATE_EXPIRE=3;
    public static final Integer DiSh_ORDER_STATE_FINISH_PAY=4;
    public static final Integer DiSh_ORDER_STATE_PRODUCED=5;
    public static final Integer DiSh_ORDER_STATE_NOT_RECEIVE=6;
    public static final Integer DiSh_ORDER_STATE_FINISH=7;
    public static final Integer DiSh_ORDER_STATE_REFUND_PROCESSING=8;
    public static final Integer DiSh_ORDER_STATE_REFUNDED=9;
    public static final Integer DiSh_ORDER_STATE_CLERK=101;
    public static final Integer DiSh_ORDER_STATE_CLERK_PAY=102;
//    public static final Integer DiSh_ORDER_STATE_DISTRIBUTION=10;

    //退单状态
    public static final Integer REFUND_STATE_NEW=1;
    public static final Integer REFUND_STATE_FAIL=2;
    public static final Integer REFUND_STATE_SUCCESS=3;

    //外卖订单状态
    public static final Integer TAKEOUT_ORDER_STATE_FINISH=10;
    public static final Integer TAKEOUT_ORDER_STATE_REFUND_PROCESSING=20;
    public static final Integer TAKEOUT_ORDER_STATE_REFUNDED=21;

    //微信退款状态

    public static final Integer WX_REFUND_APPLY_STATE_SUCCESS=1; //微信退款申请成功
    public static final Integer WX_REFUND_APPLY_STATE_FAIL=2; //微信退款申请失败,需要再发起退款申请
    public static final Integer WX_REFUND_RESULT_STATE_SUCCESS=3;  //退款成功
    public static final Integer WX_REFUND_RESULT_STATE_CLOSE=4;  //退款关闭
    public static final Integer WX_REFUND_RESULT_STATE_NOTSURE=5; //未确定,需要再发起退款申请
    public static final Integer WX_REFUND_RESULT_STATE_PROCESSING=6; //退款处理中
    public static final Integer WX_REFUND_RESULT_STATE_CHANGE=7;  //退款异常
    public static final Integer WX_REFUND_RESULT_STATE_NOTEXIST=8;  //退款不存在
    public static final Integer WX_REFUND_APPLY_STATE_ERROR=20;  //微信退款申请错误，需自行处理退款。

    //支付宝退款状态
    public static final Integer ALIPAY_REFUND_APPLY_STATE_SUCCESS=1; //支付宝退款申请成功
    public static final Integer ALIPAY_REFUND_APPLY_STATE_FAIL=2; //支付宝退款申请失败,需要再发起退款申请
    public static final Integer ALIPAY_REFUND_RESULT_STATE_SUCCESS=3; //支付宝退款成功

    //支付类型
    public static final Integer DiSh_ORDER_PAY_TYPE_WEIXIN=1;
    public static final Integer DiSh_ORDER_PAY_TYPE_ALIPAY =2;
    public static final Integer DiSh_ORDER_PAY_TYPE_CASH =3;

    //现金交易类型
    public static final Integer CASH_TRADE_TYPE_OPEN=1;
    public static final Integer CASH_TRADE_TYPE_CLOSE=2;
    public static final Integer CASH_TRADE_TYPE_WITHDRAW=3;
    //现金取现状态
    public static final Integer CASH_WITHDRAW_STATE_NEW=1;
    public static final Integer CASH_WITHDRAW_STATE_CONFIRM=2;
    public static final Integer CASH_WITHDRAW_STATE_REFUSE=3;

    //外卖类型
    public static final Integer TAKEOUT_TYPE_MEITUAN=1;
    public static final Integer TAKEOUT_TYPE_ELEME =2;

    public static final Integer DiSh_ORDER_TYPE_INSIDE=1;
    public static final Integer DiSh_ORDER_TYPE_OUTSIDE=2;

    public static final Integer RS_CLERK_TYPE_WAITER=1;
    public static final Integer RS_CLERK_TYPE_COOK=2;
    public static final Integer RS_CLERK_TYPE_CLERK=3;
    public static final Integer RS_CLERK_TYPE_CALL=4;
    public static final Integer RS_CLERK_TYPE_OWNER=5;
    public static final Integer RS_CLERK_TYPE_BOSS=6;
    public static final Integer RS_CLERK_TYPE_CASH_CLERK=7;  //现金收银店员

    public static final String MEMBER_TYPE_VERMICELLI="1";
    public static final String MEMBER_TYPE_NOODLE="2";

    public static final Integer DiSh_TYPE_ORDINARY=1;
    public static final Integer DiSh_TYPE_PACKAGE=2;
    public static final Integer DiSh_TYPE_PAKING_BOX=3;

    public static final Integer DiSh_PRINT_PRINTED=1;

    public static final Integer DiSh_PLATE_PRINT_PRINTED=1;

    public static final Integer PURCHASE_STATE_NEW=1;
    public static final Integer PURCHASE_STATE_DELIVERY=2;
    public static final Integer PURCHASE_STATE_FINISH=3;

    public static final Integer ACTIVITY_TYPE_GRANT=1;
    public static final Integer ACTIVITY_TYPE_EACHONE=2;
    public static final Integer ACTIVITY_TYPE_NEW= 3;
    public static final Integer ACTIVITY_TYPE_BUY=4;
    public static final Integer ACTIVITY_TYPE_CALL_NUM=5;
    public static final Integer ACTIVITY_TYPE_NEW_QRCODE= 6;

    public static final Integer COUPON_TYPE_CASH=1;
    public static final Integer COUPON_TYPE_FREE=2;
    public static final Integer COUPON_TYPE_DISCOUNT=3;
    //优惠券有效期类型/固定日期xxxx-xxxx
    public static final Integer COUPON_VALID_FIX_DATE=1;
    //优惠券有效期类型/自领取日固定天数
    public static final Integer COUPON_VALID_FIX_DAYS=2;

    //广告位位号
    public static final Integer AD_POSITION_ORDER= 1;
    public static final Integer AD_POSITION_TAKE_FOOD= 2;
    public static final Integer AD_POSITION_SHOPKEEPER= 3;
    public static final Integer AD_POSITION_TV= 100;
    public static final Integer AD_POSITION_PLATE= 101;

    //销售统计
    public static final int STAT_TYPE_DISH= 1;
    public static final int STAT_TYPE_MATERIAL= 2;
    public static final int STAT_TYPE_COUPON= 3;
    public static final int STAT_TYPE_ACTIVITY= 4;

    public static final int DISH_CATEGIRY_TYPE_STAPLE=1;
    public static final int DISH_CATEGIRY_TYPE_ADDITIVE=2;

    public static final int TEMPLATE_TYPE_PURCHASE=1;
    public static final int TEMPLATE_TYPE_STOCKCHECK=2;

    //门店营业状态
    public static final Integer RS_BUSINESS_STATE_CLOSE=0;
    public static final Integer RS_BUSINESS_STATE_OPEN=1;

    //二维码参数名
    public static final String PARAM_NAME_QR_ID="qrId";

    //订单来源
    public static final Integer ORDER_SOURCE_TYPE_PHONE =1;  // 手机点餐
    public static final Integer ORDER_SOURCE_TYPE_PLATE =2;   // 自助点餐
    public static final Integer ORDER_SOURCE_TYPE_CASH =3;   // 收银平板
    public static final Integer ORDER_SOURCE_TYPE_CLERK =4;   // 员工餐

    //门店区域类型
    public static final Integer RS_AREA_TYPE_CLERK =1;  // 出品区
    public static final Integer RS_AREA_TYPE_COOK =2;   // 做餐区

    //redisDB序号设置
    public static final Integer REDIS_DB_INDEX_FMLM=0; //默认为0,粉面联盟
    public static final Integer REDIS_DB_INDEX_TOKEN=1; //token


    public static final Map<String,String> DiSh_ORDER_STATE=new HashMap<String, String>() {
        {
            put(DiSh_ORDER_STATE_NEW+"", "新建");
            put(DiSh_ORDER_STATE_WAIT_PAY+"", "待支付");
            put(DiSh_ORDER_STATE_EXPIRE+"", "已过期");
            put(DiSh_ORDER_STATE_FINISH_PAY+"", "已支付");
            put(DiSh_ORDER_STATE_PRODUCED+"", "已叫号");
            put(DiSh_ORDER_STATE_NOT_RECEIVE+"", "未领取");
            put(DiSh_ORDER_STATE_FINISH+"", "已完成");
            put(DiSh_ORDER_STATE_REFUND_PROCESSING+"", "退单中");
            put(DiSh_ORDER_STATE_REFUNDED+"", "已退单");
//            put(DiSh_ORDER_STATE_DISTRIBUTION+"", "配送中");
        }
    };

    public static final Map<String,String> REFUND_STATE=new HashMap<String, String>() {
        {
            put(REFUND_STATE_NEW+"", "审核中");
            put(REFUND_STATE_FAIL+"", "退单失败");
            put(REFUND_STATE_SUCCESS+"", "退单成功");
        }
    };

    public static final Map<String,String> TAKEOUT_ORDER_STATE=new HashMap<String, String>() {
        {
            put(TAKEOUT_ORDER_STATE_FINISH+"", "已下单");
            put(TAKEOUT_ORDER_STATE_REFUND_PROCESSING+"", "退单中");
            put(TAKEOUT_ORDER_STATE_REFUNDED+"", "已退单");
        }
    };

    public static final Map<String,String> WX_REFUND_STATE=new HashMap<String, String>() {
        {
            put(WX_REFUND_APPLY_STATE_SUCCESS+"", "退款申请成功");
            put(WX_REFUND_APPLY_STATE_FAIL+"", "退款申请失败");
            put(WX_REFUND_APPLY_STATE_ERROR+"", "退款申请错误");
            put(WX_REFUND_RESULT_STATE_SUCCESS+"", "退款成功");
            put(WX_REFUND_RESULT_STATE_CLOSE+"", "退款关闭");
            put(WX_REFUND_RESULT_STATE_NOTSURE+"", "未确定");
            put(WX_REFUND_RESULT_STATE_PROCESSING+"", "退款处理中");
            put(WX_REFUND_RESULT_STATE_CHANGE+"", "退款异常");
            put(WX_REFUND_RESULT_STATE_NOTEXIST+"", "退款不存在");

        }
    };
    public static final Map<String,String> ALIPAY_REFUND_STATE=new HashMap<String, String>() {
        {
            put(ALIPAY_REFUND_APPLY_STATE_SUCCESS+"", "退款申请成功");
            put(ALIPAY_REFUND_APPLY_STATE_FAIL+"", "退款申请失败");
            put(ALIPAY_REFUND_RESULT_STATE_SUCCESS+"", "退款成功");
        }
    };
    public static final Map<String,String> DiSh_TYPE=new HashMap<String, String>() {
        {
            put(DiSh_TYPE_ORDINARY+"", "普通");
            put(DiSh_TYPE_PACKAGE+"", "套餐");
            put(DiSh_TYPE_PAKING_BOX+"", "打包盒");
        }
    };
    public static final Map<String,String> DiSh_ORDER_PAY_TYPE=new HashMap<String, String>() {
        {
            put(DiSh_ORDER_PAY_TYPE_WEIXIN+"", "微信");
            put(DiSh_ORDER_PAY_TYPE_ALIPAY +"", "支付宝");
            put(DiSh_ORDER_PAY_TYPE_CASH +"", "现金");
        }
    };
    public static final Map<String,String> CASH_TRADE_TYPE=new HashMap<String, String>() {
        {
            put(CASH_TRADE_TYPE_OPEN+"", "开档");
            put(CASH_TRADE_TYPE_CLOSE +"", "打烊");
            put(CASH_TRADE_TYPE_WITHDRAW +"", "取现");
        }
    };
    public static final Map<String,String> TAKEOUT_TYPE=new HashMap<String, String>() {
        {
            put(TAKEOUT_TYPE_MEITUAN+"", "美团外卖");
            put(TAKEOUT_TYPE_ELEME +"", "饿了么");
        }
    };

    public static final Map<String,String> DiSh_ORDER_TYPE=new HashMap<String, String>() {
        {
            put(DiSh_ORDER_TYPE_INSIDE+"", "堂食");
            put(DiSh_ORDER_TYPE_OUTSIDE+"", "外带");
        }
    };


    public static final Map<String,String> RS_CLERK_TYPE=new HashMap<String, String>() {
        {
            put(RS_CLERK_TYPE_WAITER+"", "店员");
            put(RS_CLERK_TYPE_COOK+"", "后厨");
            put(RS_CLERK_TYPE_CLERK+"", "出品");
            put(RS_CLERK_TYPE_CALL+"", "叫号屏显");
            put(RS_CLERK_TYPE_OWNER+"", "店长");
            put(RS_CLERK_TYPE_BOSS+"", "老板");
            put(RS_CLERK_TYPE_CASH_CLERK+"", "收银店员");
        }
    };
    public static final Map<String,String> ACTIVITY_TYPE=new HashMap<String, String>() {
        {
            put(ACTIVITY_TYPE_GRANT+"", "平台发放");
            put(ACTIVITY_TYPE_EACHONE+"", "正价购买赠送");
            put(ACTIVITY_TYPE_NEW+"", "新用户");
            put(ACTIVITY_TYPE_NEW_QRCODE+"", "新用户扫码关注送");
            put(ACTIVITY_TYPE_BUY+"", "买就送");
            put(ACTIVITY_TYPE_CALL_NUM+"", "取餐惊喜");

        }
    };
    public static final Map<String,String> PURCHASE_STATE=new HashMap<String, String>() {
        {
            put(PURCHASE_STATE_NEW+"", "待处理");
            put(PURCHASE_STATE_DELIVERY+"", "送货中");
            put(PURCHASE_STATE_FINISH+"", "完成");
        }
    };

    public static final Map<String,String> COUPON_TYPE=new HashMap<String, String>() {
        {
            put(COUPON_TYPE_CASH+"", "代金券");
            put(COUPON_TYPE_FREE+"", "免单券");
            put(COUPON_TYPE_DISCOUNT+"", "折扣券");
        }
    };
    public static final Map<String,String> AD_POSITION = new HashMap<String, String>() {
        {
            put(AD_POSITION_ORDER+"", "点餐页");
            put(AD_POSITION_TAKE_FOOD+"", "取餐页");
            put(AD_POSITION_SHOPKEEPER+"", "掌柜首页");
            put(AD_POSITION_TV+"", "电视广告位");
            put(AD_POSITION_PLATE+"", "自助点餐广告位");
        }
    };

    public static List<Integer> getPayedStates(){
        List<Integer> states = new ArrayList<Integer>();
        states.add(DiSh_ORDER_STATE_FINISH);
        states.add(DiSh_ORDER_STATE_FINISH_PAY);
        states.add(DiSh_ORDER_STATE_PRODUCED);
        states.add(DiSh_ORDER_STATE_NOT_RECEIVE);
        return states;
    }

    public static List<Integer> getPayedNotFinishedStates(){
        List<Integer> states = new ArrayList<Integer>();
        states.add(DiSh_ORDER_STATE_FINISH_PAY);
        states.add(DiSh_ORDER_STATE_PRODUCED);
        states.add(DiSh_ORDER_STATE_NOT_RECEIVE);
        return states;
    }

    public static List<Integer> getRefundStates(){
        List<Integer> states = new ArrayList<Integer>();
        states.add(DiSh_ORDER_STATE_REFUND_PROCESSING);
        states.add(DiSh_ORDER_STATE_REFUNDED);
        return states;
    }
    public static final Map<String,String> TEMPLATE_TYPE=new HashMap<String, String>() {
        {
            put(TEMPLATE_TYPE_PURCHASE+"", "采购单");
            put(TEMPLATE_TYPE_STOCKCHECK+"", "盘点单");
        }
    };
    public static final Map<String,String> ORDER_TYPE=new HashMap<String, String>() {
        {
            put(ORDER_SOURCE_TYPE_PHONE +"", "手机点餐");
            put(ORDER_SOURCE_TYPE_PLATE +"", "自助点餐");
            put(ORDER_SOURCE_TYPE_CASH +"", "收银平板");
        }
    };
    public static final Map<String,String> RS_AREA_TYPE=new HashMap<String, String>() {
        {
            put(RS_AREA_TYPE_CLERK +"", "出品区");
            put(RS_AREA_TYPE_COOK +"", "做餐区");
        }
    };
}
