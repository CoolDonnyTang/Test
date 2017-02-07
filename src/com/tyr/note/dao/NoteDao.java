package com.tyr.note.dao;

import java.util.List;
import java.util.Map;

import com.tyr.note.entity.Note;

public interface NoteDao {
	public abstract void moveByNoteId(Map<String,String> msg);
	public abstract void updateToDelet(String noteId);
	public abstract void updateByNoteId(Note note);
	public abstract Note findByNoteId(String noteId);
	public abstract void saveNote(Note note);
	public abstract List<Map<String,Object>> findByBookId(String bookId);
}
