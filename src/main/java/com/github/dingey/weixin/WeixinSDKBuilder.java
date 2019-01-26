package com.github.dingey.weixin;

import com.github.dingey.weixin.impl.WeixinSDKImpl;

public interface WeixinSDKBuilder {
    static WeixinSDK build() {
        return WeixinSDKImpl.build();
    }
}