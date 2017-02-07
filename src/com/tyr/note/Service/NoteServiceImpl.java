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
			result.setMsg("�ñʼǱ��»�û�д����ʼ�");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��ѯ�ʼǳɹ�");
		result.setData(list);
		return result;
	}

	@Override
	public NoteResult addNote(String noteBookId, String userId,
			String statusId, String typeId, String title) {
		NoteResult result = new NoteResult();
		if(noteBookId==null||userId==null||title==null) {
			result.setStatus(1);
			result.setMsg("����ʧ��");
			return result;
		}
		Note note = new Note();
		//ʹ��uuid��ȡ�ʼ�id
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
		//��������
		dao.saveNote(note);
		result.setStatus(0);
		result.setMsg("�����ɹ�");
		result.setData(noteId);
		return result;
	}
	
	public NoteResult NoteContent(String noteId){
		NoteResult result = new NoteResult();
		Note content = dao.findByNoteId(noteId);
		if(content == null) {
			result.setStatus(2);
			result.setMsg("���¼�����");
			return result;
		}
		if(content.getCn_note_body()==null||"".equals(content.getCn_note_body())) {
			result.setStatus(1);
			result.setMsg("�����ʼǻ�û������");
			result.setData(content);
			return result;
		}
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�");
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
		//����DAO����
		dao.updateByNoteId(note);
		//�������ض���
		NoteResult result = new NoteResult();
		result.setMsg("���³ɹ�");
		result.setStatus(0);
		return result;
	}

	@Override
	public NoteResult updateToDelet(String noteId) {
		NoteResult result = new NoteResult();
		if(noteId==null||"".equals(noteId)) {
			result.setMsg("��ѡ���Ӧ�ʼ�");
			result.setStatus(1);
			return result;
		}
		//����dao���³�ɾ������
		dao.updateToDelet(noteId);
		result.setMsg("�ѽ��ʼ��Ƶ�����վ");
		result.setStatus(0);
		return result;
	}

	@Override
	public NoteResult moveByNoteId(String noteId, String bookId) {
		NoteResult result = new NoteResult();
		if(noteId==null||bookId==null||"".equals(noteId)||"".equals(bookId)) {
			result.setMsg("ϵͳ���������µ�¼");
			result.setStatus(1);
			return result;
		}
		//��������װ��map
		Map<String,String> param = new HashMap<String, String>();
		param.put("noteId", noteId);
		param.put("bookId", bookId);
		//����dao����
		dao.moveByNoteId(param);
		result.setMsg("�ƶ��ʼǳɹ�");
		result.setStatus(0);
		return result;
	}

}
