package com.github.dingey.weixin.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Refund implements Serializable {
	private static final long serialVersionUID = 7018661863972647470L;
	// 商户号[必填]
	private String mchId;
	// 小程序、公众号等应用ID[必填]
	private String appId;
	// 商户秘钥
//	private String mchKey;

	// 微信订单号[二选一]
	private String transactionId;
	// 商户订单号[二选一]
	private String outTradeNo;
	// 商户退款单号[必填]商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
	private String outRefundNo;
	// 订单总金额，单位为分，只能为整数
	String totalFee;
	// 退款总金额，订单总金额，单位为分，只能为整数
	String refundFee;
	// 随机字符串[必填]
	private String nonceStr;
	// 签名[必填]通过签名算法计算得出的签名值
	private String sign;
	// 签名类型 ，目前支持HMAC-SHA256和MD5，默认为MD5
	private String signType;
}
