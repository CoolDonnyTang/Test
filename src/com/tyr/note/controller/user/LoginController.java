package com.tyr.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyr.note.Service.UserService;
import com.tyr.note.entity.NoteResult;

@Controller //ɨ��Controller
@RequestMapping("/user") //��·��
public class LoginController {
	
	//ע��UserServiceImpl
	@Resource
	UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody //��NoteResultת����JSON���
	public NoteResult check(String name, String pwd) throws Exception {
		//����UserServiceImpl�еķ���
		NoteResult result =
				userService.checkLogin(name, pwd);
		return result;
	}
}
