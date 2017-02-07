package com.tyr.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyr.note.Service.NoteService;
import com.tyr.note.entity.NoteResult;

@Controller
@RequestMapping("/note")
public class NoteController {
	
	//×¢Èë
	@Resource
	private NoteService service;
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult query(String bookId) {
		NoteResult result =
				service.queryNote(bookId);
		return result;
	}
}
