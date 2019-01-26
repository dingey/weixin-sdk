package com.github.dingey.weixin;

import java.util.Map;

public interface CryptoSDK extends AbstractSDK {
	/**
	 * 解密微信数据获取用户信息
	 * 
	 * @param sessionKey 数据进行加密签名的密钥
	 * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
	 * @param iv 加密算法的初始向量
	 * @return 用户信息
	 * @throws Exception 异常
	 */
	String decryptUserInfo(String sessionKey, String encryptedData, String iv) throws Exception;

	/**
	 * 生成 HMACSHA256
	 *
	 * @param data 待处理数据
	 * @param key 密钥
	 * @return 加密结果
	 * @throws Exception 异常
	 */
	String hmacSHA256(String data, String key) throws Exception;

	/**
	 * 生成 MD5
	 *
	 * @param data 待处理数据
	 * @return MD5结果
	 * @throws Exception 异常
	 */
	String md5(String data) throws Exception;

	/**
	 * 微信支付，生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
	 *
	 * @param data 待签名数据
	 * @param key API密钥
	 * @param signType 签名方式
	 * @return 签名
	 * @throws Exception 异常
	 */
	String generateSignature(final Map<String, String> data, String key, SignType signType) throws Exception;
}
