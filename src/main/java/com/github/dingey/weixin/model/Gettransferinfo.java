package com.github.dingey.weixin.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Gettransferinfo implements Serializable {
	private static final long serialVersionUID = -5641328786506537833L;
	// 商户账号appid[必填]
    private String mchid;
    // 小程序、公众号等应用ID[必填]
    private String appId;

    // 随机字符串[必填]
    private String nonceStr;
    // 签名[必填]通过签名算法计算得出的签名值MD5
    private String sign;
    // 签名类型为MD5
    // private String signType;

    // 商户订单号 商户订单号，需保持唯一性 (只能是字母或者数字，不能包含有其他字符)
    private String partnerTradeNo;
}
