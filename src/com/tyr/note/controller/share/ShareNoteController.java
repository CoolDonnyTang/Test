package com.tyr.note.controller.share;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyr.note.Service.ShareNoteService;
import com.tyr.note.entity.NoteResult;
@Controller
@RequestMapping("/share")
public class ShareNoteController {
	//×¢Èë
	@Resource
	ShareNoteService service;
	@RequestMapping("/sharenote.do")
	@ResponseBody
	public NoteResult share(String noteId) {
		NoteResult result = 
				service.shareNote(noteId);
		return result;
	}
}
