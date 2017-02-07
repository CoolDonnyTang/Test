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
	//ע��
	@Resource
	NoteBookDao dao;
	public NoteResult loadBooks(String userId) {
		//�������ض���
		NoteResult result = new NoteResult();
		List<NoteBook> books = dao.findByUserId(userId);
		if(0==books.size()) {
			result.setStatus(1);
			result.setMsg("�㻹û�д����ʼǱ�");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��ѯ�ʼǱ��ɹ�");
		result.setData(books);
		return result;
	}
	@Override
	public NoteResult addNoteBook(String bookName, String userId, String typeId) {
		NoteResult result = new NoteResult();
		//�����ʼǱ�
		NoteBook book = new NoteBook();
		book.setCn_notebook_name(bookName);
		book.setCn_user_id(userId);
		book.setCn_notebook_type_id(typeId);
		//ʹ��uuid����Id
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);
		dao.save(book);
		if((bookName==null)||(userId==null)) {
			result.setStatus(1);
			result.setMsg("����ʧ��");
			return result;
		}
		System.out.println("�����ɹ�");
		result.setStatus(0);
		result.setMsg("�����ɹ�");
		//���رʼǱ���id
		result.setData(bookId);
		return result;
	}
	
}
