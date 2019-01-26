package com.github.dingey.weixin.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.github.dingey.weixin.MpSDK;
import com.github.dingey.weixin.model.CustomMessage;
import com.github.dingey.weixin.model.TemplateMessage;

public class MpSDKImpl extends PaySDKImpl implements MpSDK {

    @Override
    public Map<String, String> token(String appid, String secret) throws Exception {
        String string = request("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret);
        return fromXml(string);
    }

    @Override
    public Map<String, Object> getAllPrivateTemplate(String accessToken) throws Exception {
        String string = request("https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=" + accessToken);
        return JSONObject.parseObject(string);
    }

    @Override
    public Map<String, Object> sendCustomMessage(String accessToken, String openid, CustomMessage message) throws Exception {
        message.setTouser(openid);
        String request = request("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken, JSONObject.toJSONString(message));
        return JSONObject.parseObject(request);
    }

    @Override
    public Map<String, Object> sendTemplateMessage(String accessToken, TemplateMessage message) throws Exception {
        String request = request("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken, JSONObject.toJSONString(message));
        return JSONObject.parseObject(request);
    }

    @Override
    public Map<String, Object> userInfo(String accessToken, String openid) throws Exception {
        String request = request("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openid + "&lang=zh_CN");
        return JSONObject.parseObject(request);
    }

    @Override
    public Map<String, Object> userInfoBatchget(String accessToken, String... openids) throws Exception {
        if (openids == null || openids.length == 0) {
            throw new IllegalArgumentException("openid不能为空");
        }
        Map<String, Object> p = new HashMap<>();
        List<Map<?, ?>> list = new ArrayList<>();
        for (String openid : openids) {
            Map<String, String> m = new HashMap<>();
            m.put("openid", openid);
            m.put("lang", "zh_CN");
            list.add(m);
        }
        p.put("user_list", list);
        String request = request("https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + accessToken, JSONObject.toJSONString(p));
        return JSONObject.parseObject(request);
    }

    @Override
    public Map<String, Object> materialBatchget(String accessToken, String type, int offset, int count) throws Exception {
        Map<String, Object> p = new HashMap<>();
        p.put("type", type);
        p.put("offset", offset);
        p.put("count", count);
        String request = request("https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + accessToken, JSONObject.toJSONString(p));
        return JSONObject.parseObject(request);
    }
}
