package com.tyr.note.controller.notebook;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyr.note.Service.NoteBookService;
import com.tyr.note.entity.NoteResult;
@Controller
@RequestMapping("/notebook")
public class NoteBooksController {
	//×¢Èë
	@Resource
	NoteBookService service;
	
	@RequestMapping("/loadbooks.do")
	@ResponseBody
	public 	NoteResult query(String userId) {
		NoteResult result =
				service.loadBooks(userId);
		return result;
	}
	
}
