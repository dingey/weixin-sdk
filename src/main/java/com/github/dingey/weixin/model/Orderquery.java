package com.github.dingey.weixin.model;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Orderquery implements Serializable {
	private static final long serialVersionUID = -7706757053052180519L;
	// 商户号[必填]
	private String mchId;
	// 小程序、公众号等应用ID[必填]
	private String appId;
	// 商户秘钥
	private String mchKey;

	// 微信订单号[二选一]
	private String transactionId;
	// 商户订单号[二选一]
	private String outTradeNo;

	// 随机字符串[必填]
	private String nonceStr;
	// 签名[必填]通过签名算法计算得出的签名值
	private String sign;
	// 签名类型 ，目前支持HMAC-SHA256和MD5，默认为MD5
	private String signType;
}
