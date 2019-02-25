package com.github.dingey.weixin.impl;

import com.github.dingey.weixin.MerchantPaySDK;
import com.github.dingey.weixin.SignType;
import com.github.dingey.weixin.WeixinException;
import com.github.dingey.weixin.model.Gettransferinfo;
import com.github.dingey.weixin.model.Transfers;

import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

public class MerchantPaySDKImpl extends PaySDKImpl implements MerchantPaySDK {
	@Override
	public Map<String, String> transfers(Transfers transfers, String mchKey, InputStream secStream) throws WeixinException {
		Map<String, String> param = BeanUtil.getMapValue(transfers);
		if (transfers.getSign() == null || transfers.getSign().isEmpty()) {
			String sign = generateSignature(param, mchKey, SignType.MD5);
			param.put("sign", sign);
		}
		String xml = toXml(param);
		String resp = request("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers", xml, secStream, mchKey);
		if (getLogger().isDebugEnabled()) {
			getLogger().debug("微信支付-企业付款，请求{},返回{}", xml, resp);
		}
		return fromXml(resp);
	}

	@Override
	public Map<String, String> gettransferinfo(Gettransferinfo transfers, String mchKey, InputStream secStream) throws WeixinException {
		if (transfers.getNonceStr() == null || transfers.getNonceStr().isEmpty()) {
			transfers.setNonceStr(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		}
		Map<String, String> param = BeanUtil.getMapValue(transfers);
		if (transfers.getSign() == null || transfers.getSign().isEmpty()) {
			String sign = generateSignature(param, mchKey, SignType.MD5);
			param.put("sign", sign);
		}
		String xml = toXml(param);
		String resp = request("https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo", xml, secStream, mchKey);
		if (getLogger().isDebugEnabled()) {
			getLogger().debug("微信支付-查询企业付款，请求{},返回{}", xml, resp);
		}
		return fromXml(resp);
	}
}
