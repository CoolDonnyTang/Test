package com.tyr.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyr.note.Service.NoteService;
import com.tyr.note.entity.NoteResult;
@Controller
@RequestMapping("/note")
public class ContentQueryController {
	//ע��
	@Resource
	NoteService service;
	@RequestMapping("/query.do")
	@ResponseBody
	public NoteResult ContentQuery(String noteId) {
		NoteResult result = 
				service.NoteContent(noteId);
		return result;
	}
}
