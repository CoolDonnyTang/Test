package com.tyr.note.Service;

import com.tyr.note.entity.NoteResult;

public interface NoteService {
	public abstract NoteResult moveByNoteId(String noteId, String bookId);
	public abstract NoteResult updateToDelet(String noteId);
	public abstract NoteResult updateByNoteId(String noteId,
			String noteTitle,String noteBody);
	public abstract NoteResult NoteContent(String noteId);
	public abstract NoteResult addNote(String noteBookId, 
			String userId,String statusId,
			String typeId,String title);
	public abstract NoteResult queryNote(String bookiD);
}
