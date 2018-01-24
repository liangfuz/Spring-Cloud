package cn.dingshen.common.utils.order;

import cn.dingshen.common.config.WeiXinConfig;
import cn.dingshen.common.utils.weixin.WeiXinToken;
import org.apache.commons.lang.time.DateFormatUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.*;


public class OrderUtilsMethod {

	public static String calculateMemberCardNum(Integer id) {
		if (id != null) {
			String idString = id.intValue() + "";
			int length = idString.length();
			if (length < 6) {
				for (int i = 0; i < 6 - length; i++) {
					idString = "0" + idString;
				}
			}
			return idString;
		} else {
			return null;
		}
	}

	public static String SHA1(String decript) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 生成订单编号
	 * 
	 * @param userId
	 * @return orderNum
	 */
	public final static String generateOrderNum(Integer userId) {
		DecimalFormat dft = new DecimalFormat("0000");
		Date date = new Date();
		String dateStr = DateFormatUtils.format(date, "yyMMddHHmmss");

		int userIdMod = userId.intValue() % 10000;
		String userIdModFormat = dft.format(userIdMod);
		Random rand = new Random();
		int randNum = rand.nextInt(100);
		String orderNum = dateStr + userIdModFormat + randNum;
		// System.out.println(orderNum + " , " + orderNum.length());
		// log.info("userId,orderNum:" + userId + "," + orderNum);
		return orderNum;
	}

	/**
	 * 生成退换货编号
	 * 
	 * @param userId
	 * @return orderNum
	 */
	public final static String generateRefundNum(Integer orderId) {
		DecimalFormat dft = new DecimalFormat("0000");
		Date date = new Date();
		String dateStr = DateFormatUtils.format(date, "yyMMddHHmmss");

		int userIdMod = orderId.intValue() % 10000;
		String userIdModFormat = dft.format(userIdMod);
		Random rand = new Random();
		int randNum = rand.nextInt(100);
		String refundNum = dateStr + userIdModFormat + randNum;
		// System.out.println(orderNum + " , " + orderNum.length());
		// log.info("userId,orderNum:" + userId + "," + orderNum);
		return refundNum;
	}

	//
	public static Map<String, String> sign(String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";
		String jsapi_ticket = WeiXinToken.getJsapiTicket();
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		ret.put("appId", WeiXinConfig.getInstance().getAppId());
		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}


}
