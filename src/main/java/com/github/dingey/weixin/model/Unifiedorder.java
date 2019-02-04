package com.github.dingey.weixin.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Unifiedorder implements Serializable {
	private static final long serialVersionUID = 4522191029274426539L;
	// 商户号[必填]
	private String mchId;
	// 小程序、公众号等应用ID[必填]
	private String appId;
	// 商户秘钥
	private String mchKey;

	// 随机字符串[必填]
	private String nonceStr;
	// 签名[必填]通过签名算法计算得出的签名值
	private String sign;
	// 签名类型 ，目前支持HMAC-SHA256和MD5，默认为MD5
	private String signType;
	// 商品简单描述
	private String body;
	// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
	private String outTradeNo;
	// 订单总金额，单位为分
	private String totalFee;
	// 终端IP 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP 123.12.12.123
	private String spbillCreateIp;
	// 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
	private String notifyUrl;
	// 交易类型小程序取值如下：JSAPI
	private String tradeType;
	// 用户标识 trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
	private String openId;
}
