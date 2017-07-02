package com.lzjian.androidutils.utils;

import java.util.UUID;

/**
 * @Description: UUID字符串生成工具
 */
public class UUIDUtils {
	public static String getUUID()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}
}
