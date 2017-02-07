package com.tyr.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyr.note.Service.NoteService;
import com.tyr.note.entity.NoteResult;

@Controller
@RequestMapping("/note")
public class AddNoteController {
	//×¢Èë
	@Resource
	NoteService service;
	
	@RequestMapping("/addnote.do")
	@ResponseBody
	public NoteResult addNote(String noteBookId, String userId,
			String statusId, String typeId, String title) {
//System.out.println(userId);
		NoteResult result = service.addNote(noteBookId, userId, statusId, typeId, title);
		return result;
				
	}
}
