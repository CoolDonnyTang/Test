package com.tyr.note.dao;

import com.tyr.note.entity.ShareNote;

public interface ShareNoteDao {
	public void share(ShareNote shareNote);
	public String findByNoteId(String noteId);
	public void updateShare(ShareNote shareNote);
}
