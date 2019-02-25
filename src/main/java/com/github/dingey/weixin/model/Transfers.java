package com.github.dingey.weixin.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Transfers implements Serializable {
	private static final long serialVersionUID = -7018946167397770319L;
	// 商户秘钥
	// private String mchKey;
	// 商户账号appid[必填]
	private String mchid;
	// 小程序、公众号等应用ID[必填]
	private String mchAppId;

	// 随机字符串[必填]
	private String nonceStr;
	// 签名[必填]通过签名算法计算得出的签名值MD5
	private String sign;
	// 签名类型 ，目前支持HMAC-SHA256和MD5，默认为MD5
	// private String signType;

	// 商户订单号 商户订单号，需保持唯一性 (只能是字母或者数字，不能包含有其他字符)
	private String partnerTradeNo;
	// 商户appid下，某用户的openid
	private String openId;
	// 校验用户姓名选项:NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名
	private String checkName;
	// 收款用户真实姓名。 如果check_name设置为FORCE_CHECK，则必填用户真实姓名
	private String reUserName;
	// 企业付款金额，单位为分
	private Integer amount;
	// 企业付款备注，必填。注意：备注中的敏感词会被转成字符*
	private String desc;
	// 该IP同在商户平台设置的IP白名单中的IP没有关联，该IP可传用户端或者服务端的IP
	private String spbillCreateIp;
}
