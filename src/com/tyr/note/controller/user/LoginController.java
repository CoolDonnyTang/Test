package com.tyr.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyr.note.Service.UserService;
import com.tyr.note.entity.NoteResult;

@Controller //扫描Controller
@RequestMapping("/user") //父路径
public class LoginController {
	
	//注入UserServiceImpl
	@Resource
	UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody //将NoteResult转换成JSON输出
	public NoteResult check(String name, String pwd) throws Exception {
		//调用UserServiceImpl中的方法
		NoteResult result =
				userService.checkLogin(name, pwd);
		return result;
	}
}
