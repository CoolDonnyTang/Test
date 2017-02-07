package com.tyr.note.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyr.note.Service.NoteService;
import com.tyr.note.entity.NoteResult;

@Controller
@RequestMapping("/note")
public class UpdateController {
	//×¢Èë
	@Resource
	NoteService service;
	@RequestMapping("/update.do")
	@ResponseBody
	public NoteResult update(String noteId, String noteTitle,
			String noteBody) throws InterruptedException {
		Thread.sleep(2*1000);
		NoteResult result = 
				service.updateByNoteId(noteId,noteTitle,noteBody);
		return result;
	}
}




