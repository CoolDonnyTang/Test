package com.tyr.note.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class NoteUtil {
	//对msg加密
	public static String md5(String msg) throws Exception {
		//利用MD5对msg加密
		MessageDigest md = 
				MessageDigest.getInstance("MD5");
		//处理对象是字节数组，返回结果也是字节数组
		byte[] output = md.digest(msg.getBytes());
		/*
		 * 将MD5处理后的字节数组使用Base64转换成字符串
		 */
		String result = Base64.encodeBase64String(output);
		return result;
	}
	
	//获取uuid
	public static String createId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
