package com.github.dingey.weixin;

import java.io.IOException;
import java.util.Map;

/**
 * 小程序SDK接口
 */
public interface AppletsSDK extends MpSDK {
	/**
	 * 获取二维码<a href="https://developers.weixin.qq.com/miniprogram/dev/api/getWXACode.html">接口 A:适用于需要的码数量较少的业务场景</a>生成小程序码，可接受 path 参数较长，生成个数受限
	 * 
	 * @param accessToken 必填,接口调用凭证
	 * @param path 必填,扫码进入的小程序页面路径，最大长度 128 字节，不能为空
	 * @param width 二维码的宽度，单位 px。最小 280px，最大 1280px;默认430
	 * @param autoColor 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调;默认false
	 * @param lineColor auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"} 十进制表示;默认{"r":0,"g":0,"b":0}
	 * @param isHyaline 是否需要透明底色，为 true 时，生成透明底色的小程序码;默认 false
	 * @return 二维码字节流/或者map对象，根据返回类型判断是否调用成功。返回属于map或null失败，byte[]字节流成功。
	 * @throws IOException IO异常
	 */
	Object getWXACode(String accessToken, String path, Integer width, Boolean autoColor, String lineColor, Boolean isHyaline) throws IOException;
	
	/**
	 * 获取二维码<a href="https://developers.weixin.qq.com/miniprogram/dev/api/getWXACodeUnlimit.html">接口 B：适用于需要的码数量极多的业务场景</a>生成小程序码，可接受页面参数较短，生成个数不受限;调用分钟频率受限(5000次/分钟)
	 * 
	 * @param accessToken 必填,接口调用凭证，必填
	 * @param scene 最大32个可见字符，只支持数字，大小写英文以及部分特殊字符，不支持%，必填
	 * @param path 扫码进入的小程序页面路径，最大长度 128 字节，为空默认跳主页面
	 * @param width 二维码的宽度，单位 px。最小 280px，最大 1280px;默认430
	 * @param autoColor 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调;默认false
	 * @param lineColor auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"} 十进制表示;默认{"r":0,"g":0,"b":0}
	 * @param isHyaline 是否需要透明底色，为 true 时，生成透明底色的小程序码;默认 false
	 * @return 二维码字节流/或者map对象，根据返回类型判断是否调用成功。返回属于map或null失败，byte[]字节流成功。
	 * @throws IOException IO异常
	 */
	Object getWXACodeUnlimit(String accessToken,String scene, String path, Integer width, Boolean autoColor, String lineColor, Boolean isHyaline) throws IOException;

	/**
	 * 获取二维码<a href="https://developers.weixin.qq.com/miniprogram/dev/api/createWXAQRCode.html">接口 C：适用于需要的码数量较少的业务场景</a>生成二维码，可接受 path 参数较长，生成个数受限
	 * 
	 * @param accessToken 必填,接口调用凭证
	 * @param path 必填,扫码进入的小程序页面路径，最大长度 128 字节，不能为空
	 * @param width 二维码的宽度，单位 px。最小 280px，最大 1280px;默认430
	 * @return 二维码字节流/或者map对象，根据返回类型判断是否调用成功。返回属于map或null失败，byte[]字节流成功。
	 * @throws IOException IO异常
	 */
	Object createWXAQRCode(String accessToken, String path, Integer width) throws IOException;
	
	/**
	 * <a href="https://developers.weixin.qq.com/miniprogram/dev/api/code2Session.html">登录凭证校验</a>
	 * 
	 * @param appid 小程序 appId
	 * @param secret 小程序 appSecret
	 * @param jsCode 登录时获取的 code
	 * @param grantType 授权类型，此处只需填写 authorization_code
	 * @return 结果
	 * @throws Exception 异常
	 */
	Map<String,Object> jscode2session(String appid, String secret, String jsCode,String grantType) throws Exception;
}
