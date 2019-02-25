package com.github.dingey.weixin.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.github.dingey.weixin.PaySDK;
import com.github.dingey.weixin.SignType;
import com.github.dingey.weixin.WeixinException;
import com.github.dingey.weixin.model.Orderquery;
import com.github.dingey.weixin.model.Refund;
import com.github.dingey.weixin.model.Unifiedorder;

public class PaySDKImpl extends CryptoSDKImpl implements PaySDK {
	PaySDKImpl() {
	}

	@Override
	public Map<String, String> unifiedorder(Unifiedorder order) throws WeixinException {
		Map<String, String> param = new HashMap<>();
		param.put("appid", order.getAppId());
		param.put("mch_id", order.getMchId());
		param.put("nonce_str", UUID.randomUUID().toString().trim().replaceAll("-", ""));
		param.put("body", order.getBody());
		param.put("out_trade_no", order.getOutTradeNo());
		param.put("total_fee", order.getTotalFee());
		param.put("spbill_create_ip", order.getSpbillCreateIp());
		param.put("notify_url", order.getNotifyUrl());
		param.put("trade_type", "JSAPI");
		param.put("openid", order.getOpenId());
		String sign = generateSignature(param, order.getMchKey(), SignType.MD5);
		param.put("sign", sign);
		String xml = toXml(param);
		String resp = request("https://api.mch.weixin.qq.com/pay/unifiedorder", xml);
		if (getLogger().isDebugEnabled()) {
			getLogger().debug("微信支付-统一下单，请求{},返回{}", xml, resp);
		}
		return fromXml(resp);
	}

	@Override
	public Map<String, String> orderquery(Orderquery order) throws WeixinException {
		Map<String, String> param = new HashMap<>();
		param.put("appid", order.getAppId());
		param.put("mch_id", order.getMchId());
		param.put("nonce_str", UUID.randomUUID().toString().trim().replaceAll("-", ""));
		if (order.getTransactionId() != null) {
			param.put("transaction_id", order.getTransactionId());
		}
		if (order.getOutTradeNo() != null) {
			param.put("out_trade_no", order.getOutTradeNo());
		}
		String sign = generateSignature(param, order.getMchKey(), SignType.MD5);
		param.put("sign", sign);
		String xml = toXml(param);
		String resp = request("https://api.mch.weixin.qq.com/pay/orderquery", xml);
		if (getLogger().isDebugEnabled()) {
			getLogger().debug("微信支付-查询订单，请求{},返回{}", xml, resp);
		}
		return fromXml(resp);
	}

	@Override
	public Map<String, String> closeorder(Orderquery order) throws WeixinException {
		Map<String, String> param = new HashMap<>();
		param.put("appid", order.getAppId());
		param.put("mch_id", order.getMchId());
		param.put("nonce_str", UUID.randomUUID().toString().trim().replaceAll("-", ""));
		param.put("out_trade_no", order.getOutTradeNo());
		String sign = generateSignature(param, order.getMchKey(), SignType.MD5);
		param.put("sign", sign);
		String xml = toXml(param);
		String resp = request("https://api.mch.weixin.qq.com/pay/closeorder", xml);
		if (getLogger().isDebugEnabled()) {
			getLogger().debug("微信支付-关闭订单，请求{},返回{}", xml, resp);
		}
		return fromXml(resp);
	}

	@Override
	public Map<String, String> refund(Refund refund, String mchKey, InputStream secStream) throws WeixinException {
		Map<String, String> param = new HashMap<>();
		param.put("appid", refund.getAppId());
		param.put("mch_id", refund.getMchId());
		param.put("nonce_str", UUID.randomUUID().toString().trim().replaceAll("-", ""));
		param.put("out_trade_no", refund.getOutTradeNo());
		param.put("out_refund_no", refund.getOutRefundNo());
		param.put("refund_fee", refund.getRefundFee());
		param.put("total_fee", refund.getTotalFee());
		param.put("transaction_id", refund.getTransactionId());
		String sign = generateSignature(param, mchKey, SignType.MD5);
		param.put("sign", sign);
		String xml = toXml(param);
		String resp = request("https://api.mch.weixin.qq.com/secapi/pay/refund", xml, secStream, mchKey);
		if (getLogger().isDebugEnabled()) {
			getLogger().debug("微信支付-申请退款，请求{},返回{}", xml, resp);
		}
		return fromXml(resp);
	}

	@Override
	public boolean verifyNotify(Map<String, String> data, String key) throws WeixinException {
		if (!data.containsKey("sign")) {
			return false;
		}
		String sign = data.get("sign");
		return sign.equals(generateSignature(data, key, data.get("sign_type") != null ? SignType.valueOf(data.get("sign_type")) : SignType.MD5));
	}
}
