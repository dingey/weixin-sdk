package com.github.dingey.weixin;

public class WeixinException extends RuntimeException {
	private static final long serialVersionUID = -8372155484635416783L;

	public WeixinException(Throwable cause) {
		super(cause);
	}

	public WeixinException(String message, Exception e) {
		super(message, e);
	}
}
