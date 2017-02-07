package com.tyr.note.test;

import java.util.List;
import java.util.Map;

import com.tyr.note.dao.NoteDao;

public class Test extends TestBean {

	public static void main(String[] args) throws Exception {
		/*//AbstractApplicationContext类的对象aac来自父类TestBean
		UserDao dao = aac.getBean("userDao", UserDao.class);
		
		User user = dao.findByName("tom");
		System.out.println(user);
		System.out.println(NoteUtil.md5("1234"));
		User user = new User();
		user.setCn_user_name("tyr");
		//user.setCn_user_desc("1234");
		//加密密码
		//String md5_pwd = NoteUtil.md5(pwd);
		user.setCn_user_pwd("1234");
		//使用工具类获取id
		String userId = NoteUtil.createId();
		System.out.print(userId + " " + user.getCn_user_token());
		user.setCn_user_id(userId);
		user.setCn_user_token(null);
		//调用UserDao保存对象
		dao.save(user);*/
		/*NoteBookDao dao = aac.getBean("noteBookDao", NoteBookDao.class);
		List<NoteBook> books = dao.findByUserId("36sd4b021-8d6b-4ae0-8214-2a53ffa23ec4");
		System.out.println(books.size());
		if(books.size() == 0) {
			System.out.println("books为空");
		}
		for(NoteBook n:books) {
			System.out.println(n);
		
		}*/
		NoteDao dao = aac.getBean("noteDao", NoteDao.class);
		List<Map<String,Object>> ns = dao.findByBookId("1 0011");
		System.out.println(ns.size());
		if(ns.size() == 0) {
			System.out.println("笔记为空");
		}
		for(Map<String,Object> n:ns) {
			System.out.println(n);
		
		}
	}
}
