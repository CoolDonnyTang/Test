package com.tyr.note.dao;

import java.util.List;

import com.tyr.note.entity.NoteBook;

public interface NoteBookDao {
	public abstract List<NoteBook> findByUserId(String userId);
	public abstract void save(NoteBook book);
}
