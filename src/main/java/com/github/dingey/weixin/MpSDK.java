package com.github.dingey.weixin;

import java.util.Map;

import com.github.dingey.weixin.model.CustomMessage;
import com.github.dingey.weixin.model.TemplateMessage;

public interface MpSDK extends MerchantPaySDK {
    /**
     * <a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183">获取access_token</a>
     *
     * @param appid  第三方用户唯一凭证
     * @param secret 第三方用户唯一凭证密钥，即appsecret
     * @return json结果
     * @throws WeixinException 异常
     */
    Map<String, String> token(String appid, String secret) throws WeixinException;

    /**
     * <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277">获取模板列表</a>
     *
     * @param accessToken 接口调用凭证
     * @return json结果
     * @throws WeixinException 异常
     */
    Map<String, Object> getAllPrivateTemplate(String accessToken) throws WeixinException;

    /**
     * <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140547">客服接口-发消息</a>
     *
     * @param accessToken 接口调用凭证
     * @param openid      用户
     * @param message     消息
     * @return json结果
     * @throws WeixinException 异常
     */
    Map<String, Object> sendCustomMessage(String accessToken, String openid, CustomMessage message) throws WeixinException;

    /**
     * <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277">发送模板消息</a>
     *
     * @param accessToken 接口调用凭证
     * @param message     参数
     * @return json结果
     * @throws WeixinException 异常
     */
    Map<String, Object> sendTemplateMessage(String accessToken, TemplateMessage message) throws WeixinException;

    /**
     * 获取用户基本信息(UnionID机制)<a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839">获取用户基本信息(UnionID机制)</a>
     *
     * @param accessToken 接口调用凭证
     * @param openid      参数
     * @return json结果
     * @throws WeixinException 异常
     */
    Map<String, Object> userInfo(String accessToken, String openid) throws WeixinException;

    /**
     * <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839">批量获取用户基本信息</a>
     *
     * @param accessToken 接口调用凭证
     * @param openid      参数
     * @return json结果
     * @throws WeixinException 异常
     */
    Map<String, Object> userInfoBatchget(String accessToken, String... openid) throws WeixinException;

    /**
     * <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738734">获取素材列表</a>
     *
     * @param accessToken 接口调用凭证
     * @param type        素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset      从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count       返回素材的数量，取值在1到20之间
     * @return json结果
     * @throws WeixinException 异常
     */
    Map<String, Object> materialBatchget(String accessToken, String type, int offset, int count) throws WeixinException;
}
