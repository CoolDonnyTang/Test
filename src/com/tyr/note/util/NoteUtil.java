package com.tyr.note.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class NoteUtil {
	//��msg����
	public static String md5(String msg) throws Exception {
		//����MD5��msg����
		MessageDigest md = 
				MessageDigest.getInstance("MD5");
		//����������ֽ����飬���ؽ��Ҳ���ֽ�����
		byte[] output = md.digest(msg.getBytes());
		/*
		 * ��MD5�������ֽ�����ʹ��Base64ת�����ַ���
		 */
		String result = Base64.encodeBase64String(output);
		return result;
	}
	
	//��ȡuuid
	public static String createId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
