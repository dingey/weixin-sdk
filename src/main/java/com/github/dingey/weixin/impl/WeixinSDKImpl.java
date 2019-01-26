package com.github.dingey.weixin.impl;

import com.github.dingey.weixin.WeixinSDK;

public class WeixinSDKImpl extends MpSDKImpl implements WeixinSDK {
    private WeixinSDKImpl() {
    }

    public static WeixinSDK build() {
        return new WeixinSDKImpl();
    }
}
