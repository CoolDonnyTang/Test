package com.tyr.note.controller.notebook;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyr.note.Service.NoteBookService;
import com.tyr.note.entity.NoteResult;
@Controller
@RequestMapping("/notebook")
public class AddNoteBookController {
	//×¢Èë
	@Resource
	NoteBookService service;
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult saveBook(String bookName, String userId, String typeId) {
		NoteResult result = 
				service.addNoteBook(bookName, userId, typeId);
		return result;
	}
}
