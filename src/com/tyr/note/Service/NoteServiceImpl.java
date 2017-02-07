package com.tyr.note.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tyr.note.dao.NoteDao;
import com.tyr.note.entity.Note;
import com.tyr.note.entity.NoteResult;
import com.tyr.note.util.NoteUtil;
@Service
public class NoteServiceImpl implements NoteService {
	
	@Resource
	private NoteDao dao;
	
	public NoteResult queryNote(String bookId) {
		NoteResult result = new NoteResult();
		List<Map<String,Object>> list =
				dao.findByBookId(bookId);
		if(list.size()==0) {
			result.setStatus(1);
			result.setMsg("该笔记本下还没有创建笔记");
			return result;
		}
		result.setStatus(0);
		result.setMsg("查询笔记成功");
		result.setData(list);
		return result;
	}

	@Override
	public NoteResult addNote(String noteBookId, String userId,
			String statusId, String typeId, String title) {
		NoteResult result = new NoteResult();
		if(noteBookId==null||userId==null||title==null) {
			result.setStatus(1);
			result.setMsg("创建失败");
			return result;
		}
		Note note = new Note();
		//使用uuid获取笔记id
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(noteBookId);
		note.setCn_user_id(userId);
		note.setCn_note_status_id(statusId);
		note.setCn_note_type_id(typeId);
		note.setCn_note_title(title);
		Timestamp lastTime =  new Timestamp(
						System.currentTimeMillis());
		note.setCn_note_last_modify_time(lastTime);
		//插入数据
		dao.saveNote(note);
		result.setStatus(0);
		result.setMsg("创建成功");
		result.setData(noteId);
		return result;
	}
	
	public NoteResult NoteContent(String noteId){
		NoteResult result = new NoteResult();
		Note content = dao.findByNoteId(noteId);
		if(content == null) {
			result.setStatus(2);
			result.setMsg("请登录后操作");
			return result;
		}
		if(content.getCn_note_body()==null||"".equals(content.getCn_note_body())) {
			result.setStatus(1);
			result.setMsg("该条笔记还没有内容");
			result.setData(content);
			return result;
		}
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(content);
		return result;
	}

	@Override
	public NoteResult updateByNoteId(String noteId, String noteTitle,
			String noteBody) {
		Note note = new Note();
		note.setCn_note_body(noteBody);
		note.setCn_note_title(noteTitle);
		note.setCn_note_id(noteId);
		Timestamp time = 
				new Timestamp(System.currentTimeMillis());
		note.setCn_note_last_modify_time(time);
		//调用DAO更新
		dao.updateByNoteId(note);
		//构建返回对象
		NoteResult result = new NoteResult();
		result.setMsg("更新成功");
		result.setStatus(0);
		return result;
	}

	@Override
	public NoteResult updateToDelet(String noteId) {
		NoteResult result = new NoteResult();
		if(noteId==null||"".equals(noteId)) {
			result.setMsg("请选择对应笔记");
			result.setStatus(1);
			return result;
		}
		//调用dao更新成删除类型
		dao.updateToDelet(noteId);
		result.setMsg("已将笔记移到回收站");
		result.setStatus(0);
		return result;
	}

	@Override
	public NoteResult moveByNoteId(String noteId, String bookId) {
		NoteResult result = new NoteResult();
		if(noteId==null||bookId==null||"".equals(noteId)||"".equals(bookId)) {
			result.setMsg("系统出错，请重新登录");
			result.setStatus(1);
			return result;
		}
		//将参数封装成map
		Map<String,String> param = new HashMap<String, String>();
		param.put("noteId", noteId);
		param.put("bookId", bookId);
		//调用dao更新
		dao.moveByNoteId(param);
		result.setMsg("移动笔记成功");
		result.setStatus(0);
		return result;
	}

}
