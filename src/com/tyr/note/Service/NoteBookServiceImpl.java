package com.tyr.note.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tyr.note.dao.NoteBookDao;
import com.tyr.note.entity.NoteBook;
import com.tyr.note.entity.NoteResult;
import com.tyr.note.util.NoteUtil;
@Service
public class NoteBookServiceImpl implements NoteBookService {
	//注入
	@Resource
	NoteBookDao dao;
	public NoteResult loadBooks(String userId) {
		//创建返回对象
		NoteResult result = new NoteResult();
		List<NoteBook> books = dao.findByUserId(userId);
		if(0==books.size()) {
			result.setStatus(1);
			result.setMsg("你还没有创建笔记本");
			return result;
		}
		result.setStatus(0);
		result.setMsg("查询笔记本成功");
		result.setData(books);
		return result;
	}
	@Override
	public NoteResult addNoteBook(String bookName, String userId, String typeId) {
		NoteResult result = new NoteResult();
		//创建笔记本
		NoteBook book = new NoteBook();
		book.setCn_notebook_name(bookName);
		book.setCn_user_id(userId);
		book.setCn_notebook_type_id(typeId);
		//使用uuid生成Id
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);
		dao.save(book);
		if((bookName==null)||(userId==null)) {
			result.setStatus(1);
			result.setMsg("创建失败");
			return result;
		}
		System.out.println("创建成功");
		result.setStatus(0);
		result.setMsg("创建成功");
		//返回笔记本的id
		result.setData(bookId);
		return result;
	}
	
}
