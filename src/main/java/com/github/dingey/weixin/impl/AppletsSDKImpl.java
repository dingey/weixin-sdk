package com.github.dingey.weixin.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import com.github.dingey.weixin.AppletsSDK;

public class AppletsSDKImpl extends MpSDKImpl implements AppletsSDK {

	@Override
	public Object getWXACode(String accessToken, String path, Integer width, Boolean autoColor, String lineColor, Boolean isHyaline) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("path=").append(path);
		if (width != null) {
			sb.append("&").append("width=").append(width);
		}
		if (autoColor != null) {
			sb.append("&").append("auto_color=").append(autoColor);
		}
		if (lineColor != null) {
			sb.append("&").append("line_color=").append(lineColor);
		}
		if (isHyaline != null) {
			sb.append("&").append("is_hyaline=").append(isHyaline);
		}
		HttpURLConnection conn = connect("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken, sb.toString(), null);
		InputStream inputStream = conn.getInputStream();
		byte[] array = toByteArray(inputStream);
		if (getLogger().isDebugEnabled()) {
			getLogger().debug("请求地址{},请求参数{},返回头{}", path, sb.toString(), conn.getContentType());
		}
		if (conn.getContentType().contains("jpeg")) {
			return array;
		} else {
			getLogger().warn("请求地址{},请求参数{},返回头{},返回内容{}", path, sb.toString(), conn.getContentType(), new String(array, "utf-8"));
		}
		return toJson(new String(array, "utf-8"));
	}

	@Override
	public Object getWXACodeUnlimit(String accessToken, String scene, String path, Integer width, Boolean autoColor, String lineColor, Boolean isHyaline) throws IOException {
		Map<String, Object> param = new HashMap<>();
		param.put("scene", scene);
		if (path != null) {
			param.put("path", path);
		}
		if (width != null) {
			param.put("width", width);
		}
		if (autoColor != null) {
			param.put("auto_color", autoColor);
		}
		if (lineColor != null) {
			param.put("line_color", autoColor);
		}
		if (isHyaline != null) {
			param.put("is_hyaline", isHyaline);
		}
		String paramStr = toJson(param);
		HttpURLConnection conn = connect("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken, paramStr, null);
		InputStream inputStream = conn.getInputStream();
		byte[] array = toByteArray(inputStream);
		if (getLogger().isDebugEnabled()) {
			getLogger().debug("请求地址{},请求参数{},返回头{}", path, paramStr, conn.getContentType());
		}
		if (conn.getContentType().contains("jpeg")) {
			return array;
		} else {
			getLogger().warn("请求地址{},请求参数{},返回头{},返回内容{}", path, paramStr, conn.getContentType(), new String(array, "utf-8"));
		}
		return fromJson(new String(array, "utf-8"));
	}

	@Override
	public Object createWXAQRCode(String accessToken, String path, Integer width) throws IOException {
		Map<String, Object> param = new HashMap<>();
		param.put("path", path);
		if (width != null) {
			param.put("width", width);
		}
		String paramStr = toJson(param);
		HttpURLConnection conn = connect("https://api.weixin.qq.com/wxa/createWXAQRCode?access_token=" + accessToken, paramStr, null);
		InputStream inputStream = conn.getInputStream();
		byte[] array = toByteArray(inputStream);
		if (getLogger().isDebugEnabled()) {
			getLogger().debug("请求地址{},请求参数{},返回头{}", path, paramStr, conn.getContentType());
		}
		if (conn.getContentType().contains("jpeg")) {
			return array;
		} else {
			getLogger().warn("请求地址{},请求参数{},返回头{},返回内容{}", path, paramStr, conn.getContentType(), new String(array, "utf-8"));
		}
		return fromJson(new String(array, "utf-8"));
	}

	@Override
	public Map<String, Object> jscode2session(String appid, String secret, Integer jsCode, String grantType) throws Exception {
		String resp = request("https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=" + grantType == null? "authorization_code": grantType);
		return fromJson(resp);
	}
}
