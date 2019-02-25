package com.github.dingey.weixin.impl;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.dingey.weixin.WeixinException;

class BeanUtil {
	public static <T> Map<String, String> getMapValue(T bean) {
		Map<String, String> m = new LinkedHashMap<>();
		for (Field f : Json.getDeclaredFields(bean.getClass())) {
			if (!f.isAccessible())
				f.setAccessible(true);
			try {
				Object v = f.get(bean);
				if (v == null)
					continue;
				m.put(Json.snakeCase(f.getName()), String.valueOf(v));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new WeixinException("获取属性值异常：" + f.getName(), e);
			}
		}
		return m;
	}
}
