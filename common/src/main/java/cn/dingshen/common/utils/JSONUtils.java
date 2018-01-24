package cn.dingshen.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * json解析处理工具类
 *
 * @author jiangrujie
 * @create 2017-04-06 上午 10:24
 **/
public class JSONUtils {
    /***
     * json字符串转java List
     * @param content
     * @return
     * @throws Exception
     */
    public static List<Map<String, String>> jsonArrayToList(String content) throws Exception
    {
        JSONArray arry = JSONArray.fromObject(content);
        List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < arry.size(); i++)
        {
            JSONObject jsonObject = arry.getJSONObject(i);
            Map<String, String> map = new HashMap<String, String>();
            for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();)
            {
                String key = (String) iter.next();
                String value = jsonObject.get(key).toString();
                map.put(key, value);
            }
            rsList.add(map);
        }
        return rsList;
    }
}
