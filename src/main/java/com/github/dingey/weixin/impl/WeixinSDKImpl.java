package com.github.dingey.weixin.impl;

import com.github.dingey.weixin.WeixinSDK;

public class WeixinSDKImpl extends AppletsSDKImpl implements WeixinSDK {
    private WeixinSDKImpl() {
    }

    public static WeixinSDK build() {
        return new WeixinSDKImpl();
    }
}
