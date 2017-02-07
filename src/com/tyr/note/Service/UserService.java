package com.tyr.note.Service;

import com.tyr.note.entity.NoteResult;

public interface UserService {
	public NoteResult checkLogin(String name, String pwd) throws Exception;
	public NoteResult regist(
			String name, String pwd, String nickname) throws Exception;
}
