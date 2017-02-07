package com.tyr.note.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyr.note.Service.UserService;
import com.tyr.note.entity.NoteResult;

@Controller
@RequestMapping("/user")
public class RegistController {
	//×¢Èë
	@Resource
	UserService service;
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public NoteResult regist(String name, 
			String pwd, String nickname) throws Exception {
		NoteResult result =
				service.regist(name, pwd, nickname);
		return result;
	}
}
