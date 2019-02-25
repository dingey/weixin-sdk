package com.github.dingey.weixin;

import com.github.dingey.weixin.model.Gettransferinfo;
import com.github.dingey.weixin.model.Transfers;

import java.io.InputStream;
import java.util.Map;

public interface MerchantPaySDK extends PaySDK {
    /**
     * <a href="https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2">企业付款到零钱</a>
     *
     * @param transfers 参数
     * @param mchKey    商户秘钥
     * @param secStream 证书流
     * @return 结果
     * @throws WeixinException 异常
     */
    Map<String, String> transfers(Transfers transfers, String mchKey, InputStream secStream) throws WeixinException;

    /**
     * <a href="https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_3">查询企业付款</a>
     *
     * @param transfers 参数
     * @param mchKey    商户秘钥
     * @param secStream 证书流
     * @return 结果
     * @throws WeixinException 异常
     */
    Map<String, String> gettransferinfo(Gettransferinfo transfers, String mchKey, InputStream secStream) throws WeixinException;
}
