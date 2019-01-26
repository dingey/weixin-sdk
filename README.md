# weixin-sdk
微信支付、公众号SDK工具类

# 引入依赖
```
<dependency>
    <groupId>com.github.dingey</groupId>
    <artifactId>weixin-sdk</artifactId>
    <version>0.0.1</version>
</dependency>    
```
# 使用sdk
```
WeixinSDK weixinSDK = WeixinSDKBuilder.build();

```
配置为spring
```
@Bean
public WeixinSDK weixinSDK() {
    return WeixinSDKBuilder.build();
}
```
