package com.tyr.note.Service;

import com.tyr.note.entity.NoteResult;

public interface NoteBookService {
	public abstract NoteResult loadBooks(String userId);
	public abstract NoteResult addNoteBook(String bookName,String userId,String typeId);
}
