package com.github.dingey.weixin.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class TemplateMessage implements Serializable {
	private static final long serialVersionUID = -1569251036795508330L;
	// 接收者openid 必填
	private String touser;
	// 模板ID 必填
	private String template_id;
	// 模板跳转链接（海外帐号没有跳转能力）
	private String url;
	// 跳小程序所需数据，不需跳小程序可不用传该数据
	private String miniprogram;
	// 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏） 必填
	private String appid;
	// 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），暂不支持小游戏
	private String pagepath;
	// 模板数据 必填
	private Object data;
	// 模板内容字体颜色，不填默认为黑色
	private String color;
}
