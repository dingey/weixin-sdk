package com.github.dingey.weixin.impl;

import java.security.Key;
import java.security.MessageDigest;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.github.dingey.weixin.CryptoSDK;
import com.github.dingey.weixin.SignType;

public class CryptoSDKImpl extends AbstractSDKImpl implements CryptoSDK {
	CryptoSDKImpl() {
	}

	public String decryptUserInfo(String sessionKey, String encryptedData, String iv) throws Exception {
		Objects.requireNonNull(sessionKey, "sessionKey密钥不能为空");
		Objects.requireNonNull(sessionKey, "encryptedData加密数据不能为空");
		Objects.requireNonNull(sessionKey, "iv初始向量不能为空");
		byte[] keybytes = Base64.getDecoder().decode(sessionKey);
		byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
		byte[] ivBytes = Base64.getDecoder().decode(iv);
		int base = 16;
		if (keybytes.length % base != 0) {
			int groups = keybytes.length / base + (keybytes.length % base != 0 ? 1 : 0);
			byte[] tmp = new byte[groups * base];
			Arrays.fill(tmp, (byte) 0);
			System.arraycopy(keybytes, 0, tmp, 0, keybytes.length);
			keybytes = tmp;
		}
		// 初始化
		Security.addProvider(new BouncyCastleProvider());
		// 转化成JAVA的密钥格式
		// 算法名称
		String KEY_ALGORITHM = "AES";
		Key key = new SecretKeySpec(keybytes, KEY_ALGORITHM);
		// 初始化cipher
		// 加解密算法/模式/填充方式
		String algorithmStr = "AES/CBC/PKCS7Padding";
		Cipher cipher = Cipher.getInstance(algorithmStr);
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivBytes));
		return new String(cipher.doFinal(encryptedBytes), "UTF-8");
	}

	@Override
	public String hmacSHA256(String data, String key) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		sha256_HMAC.init(secret_key);
		byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	@Override
	public String md5(String data) throws Exception {
		java.security.MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	@Override
	public String generateSignature(Map<String, String> data, String key, SignType signType) throws Exception {
		Set<String> keySet = data.keySet();
		String[] keyArray = keySet.toArray(new String[keySet.size()]);
		Arrays.sort(keyArray);
		StringBuilder sb = new StringBuilder();
		for (String k : keyArray) {
			if (k.equals("sign")) {
				continue;
			}
			if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
				sb.append(k).append("=").append(data.get(k).trim()).append("&");
		}
		sb.append("key=").append(key);
		if (SignType.MD5.equals(signType)) {
			return md5(sb.toString()).toUpperCase();
		} else if (SignType.HMACSHA256.equals(signType)) {
			return hmacSHA256(sb.toString(), key);
		} else {
			throw new RuntimeException(String.format("Invalid sign_type: %s", signType));
		}
	}
}
