package com.tyr.note.dao;

import com.tyr.note.entity.User;

public interface UserDao {
	public abstract User findByName(String name);
	public abstract void save(User user);
}
