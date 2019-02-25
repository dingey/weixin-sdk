package com.github.dingey.weixin.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.github.dingey.weixin.AbstractSDK;
import com.github.dingey.weixin.WeixinException;

public abstract class AbstractSDKImpl implements AbstractSDK {
	private Logger log;

	Logger getLogger() {
		if (log == null) {
			log = LoggerFactory.getLogger(this.getClass());
		}
		return log;
	}

	String toXml(Map<String, String> map) {
		StringBuilder xml = new StringBuilder("<xml>");
		for (String k : map.keySet()) {
			String v = map.get(k);
			if (v == null)
				continue;
			xml.append("<").append(k).append(">");
			if (v.contains("<") || v.contains("&") || v.contains(">") || v.contains("'") || v.contains("\"")) {
				xml.append("<![CDATA[").append(v).append("]]>");
			}
			xml.append("</").append(k).append(">");
		}
		return xml.append("</xml>").toString();
	}

	Map<String, String> fromXml(String xml) {
		try {
			DocumentBuilderFactory factory = safeFactory();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document parse = builder.parse(new ByteArrayInputStream(xml.getBytes()));
			Element element = parse.getDocumentElement();
			Map<String, String> m = new LinkedHashMap<>();
			if (parse.hasChildNodes()) {
				NodeList nodes = element.getChildNodes();
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						m.put(node.getNodeName(), node.getTextContent());
					}
				}
			}
			return m;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static DocumentBuilderFactory factory;

	private DocumentBuilderFactory safeFactory() {
		if (factory == null) {
			factory = DocumentBuilderFactory.newInstance();
			try {
				factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
				factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
				factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
				factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
				factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
				factory.setXIncludeAware(false);
				factory.setExpandEntityReferences(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return factory;
	}

	String request(String path) throws WeixinException {
		return request(path, null, null);
	}

	String request(String path, String param) throws WeixinException {
		return request(path, param, null);
	}

	String request(String path, String param, InputStream sec, String key) throws WeixinException {
		try {
			return request(path, param, getSslSocketFactory(sec, key));
		} catch (Exception e) {
			throw new WeixinException(e);
		}
	}

	String request(String path, String param, SSLSocketFactory sslSocketFactory) throws WeixinException {
		try {
			HttpURLConnection conn = connect(path, param, sslSocketFactory);
			String resp;
			resp = new String(toByteArray(conn), "UTF-8");

			if (getLogger().isDebugEnabled()) {
				getLogger().debug("请求地址{},请求参数{},返回{}", path, param, resp);
			}
			return resp;
		} catch (UnsupportedEncodingException e) {
			throw new WeixinException(e);
		}
	}

	HttpURLConnection connect(String urlPath, String param, SSLSocketFactory sslSocketFactory) throws WeixinException {
		try {
			URL url = new URL(urlPath);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			if (sslSocketFactory != null) {
				conn.setSSLSocketFactory(sslSocketFactory);
			}
			if (param == null || param.isEmpty()) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStream outStream = conn.getOutputStream();
				outStream.write(param.getBytes("UTF-8"));
				outStream.flush();
				outStream.close();
			}
			conn.setConnectTimeout(5000);
			return conn;
		} catch (IOException e) {
			throw new WeixinException("创建http连接异常", e);
		}
	}

	static byte[] toByteArray(HttpURLConnection c) throws WeixinException {
		try {
			InputStream input = c.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			try {
				byte[] buffer = new byte[256];
				int n = 0;
				while (-1 != (n = input.read(buffer))) {
					output.write(buffer, 0, n);
				}
			} finally {
				input.close();
				c.disconnect();
			}
			return output.toByteArray();
		} catch (IOException e) {
			throw new WeixinException("读取流异常", e);
		}
	}

	private SSLSocketFactory getSslSocketFactory(InputStream sec, String key) throws Exception {
		SSLContext sslContext = SSLContext.getInstance("TLS");
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(sec, key.toCharArray());
		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(keyStore, key.toCharArray());
		sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
		return sslContext.getSocketFactory();
	}

	<T> String toJson(T data) {
		return Json.toJsonString(data);
	}

	@SuppressWarnings("unchecked")
	Map<String, Object> fromJson(String json) {
		return fromJson(json, Map.class);
	}

	<T> T fromJson(String json, Class<T> c) {
		return Json.fromJson(json, c);
	}

	String toString(byte[] bs, String charset) {
		try {
			return new String(bs, charset);
		} catch (UnsupportedEncodingException e) {
			throw new WeixinException(e);
		}
	}
}
