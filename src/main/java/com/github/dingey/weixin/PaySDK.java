package com.github.dingey.weixin;

import java.io.InputStream;
import java.util.Map;

import com.github.dingey.weixin.model.Orderquery;
import com.github.dingey.weixin.model.Refund;
import com.github.dingey.weixin.model.Unifiedorder;

public interface PaySDK extends CryptoSDK {
    /**
     * <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1">统一下单JSAPI、JSSDK、小程序</a>
     *
     * @param order 参数
     * @return 通信标识return_code:SUCCESS/FAIL;业务结果result_code:SUCCESS/FAIL
     * @throws WeixinException 异常
     */
    Map<String, String> unifiedorder(Unifiedorder order) throws WeixinException;

    /**
     * <a href="https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=9_2">查询订单</a>
     *
     * @param order 参数
     * @return 通信标识return_code:SUCCESS/FAIL;业务结果result_code:SUCCESS/FAIL
     * @throws WeixinException 异常
     */
    Map<String, String> orderquery(Orderquery order) throws WeixinException;

    /**
     * <a href="https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=9_3">关闭订单</a>最短调用时间间隔为5分钟
     *
     * @param order 参数
     * @return 通信标识return_code:SUCCESS/FAIL;业务结果result_code:SUCCESS/FAIL
     * @throws WeixinException 异常
     */
    Map<String, String> closeorder(Orderquery order) throws WeixinException;

    /**
     * <a href="https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=9_4">申请退款</a>
     *
     * @param refund    参数
     * @param secStream 证书
     * @return 通信标识return_code:SUCCESS/FAIL;业务结果result_code:SUCCESS/FAIL
     * @throws WeixinException 异常
     */
    Map<String, String> refund(Refund refund,String mchKey, InputStream secStream) throws WeixinException;

    /**
     * 支付通知校验
     *
     * @param param 参数
     * @param key   秘钥
     * @return true/false
     * @throws WeixinException 异常
     */
    boolean verifyNotify(Map<String, String> param, String key) throws WeixinException;
}
