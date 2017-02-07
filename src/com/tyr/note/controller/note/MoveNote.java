package com.tyr.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyr.note.Service.NoteService;
import com.tyr.note.entity.NoteResult;

@Controller
@RequestMapping("/note")
public class MoveNote {
	//×¢Èë
	@Resource
	NoteService service;
	@RequestMapping("/move.do")
	@ResponseBody
	public NoteResult moveNote(String noteId, String bookId) {
		NoteResult result = 
				service.moveByNoteId(noteId, bookId);
		return result;
	}
}
