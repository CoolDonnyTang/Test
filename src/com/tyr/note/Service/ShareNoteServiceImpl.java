package com.tyr.note.Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tyr.note.dao.NoteDao;
import com.tyr.note.dao.ShareNoteDao;
import com.tyr.note.entity.Note;
import com.tyr.note.entity.NoteResult;
import com.tyr.note.entity.ShareNote;
import com.tyr.note.util.NoteUtil;
@Service
public class ShareNoteServiceImpl implements ShareNoteService{
	//ע��
	@Resource
	NoteDao noteDao;
	@Resource
	ShareNoteDao shareNoteDao;
	
	public NoteResult shareNote(String noteId) {
		NoteResult result = new NoteResult();
		ShareNote shareNote = new ShareNote();
		if(null == noteId||"".equals(noteId)) {
			result.setMsg("��ѡ��Ҫ����ıʼ� ");
			result.setStatus(1);
			return result;
		}
		//����noteId��ѯ������ıʼǵı��������
		Note note = noteDao.findByNoteId(noteId);
		///���δ�ҵ���Ӧ�ʼ��򷵻���Ӧ��ʾ
		if(note == null) {
			result.setMsg("����ʧ�� Ҫ����ıʼǲ�����");
			result.setStatus(2);
			return result;
		}
		/*
		 * �жϸ��������ڷ�������Ƿ����
		 */
		//ʹ��noteId�ڷ�����в�ѯ
		String title = shareNoteDao.findByNoteId(noteId);
//System.out.println(title);
		if(!(title==null&&"".equals(title))) {
			/*
			 * ��������˵������������ڣ�ֻ��Ҫupdate�����£�����
			 * ���Ͻ���Ӧ���жϱ���������Ƿ�ı䣬���ı�����update�����£�
			 * �ڴ�Ϊ��Լʱ���ҾͲ�ȡ���ж�ֱ��ִ�и���
			 */
			shareNote.setCn_share_title(note.getCn_note_title());
			shareNote.setCn_share_body(note.getCn_note_body());
			shareNote.setCn_note_id(noteId);
			shareNoteDao.updateShare(shareNote);
			//����ִ�к���
			result.setMsg("���·���ɹ�");
			result.setStatus(0);
			return result;
		}
		/*
		 * ��û�����ʼ�û�б�������򴴽�����
		 */
		//���ҵ�����Ϣ����shareNote�Ķ���
		shareNote.setCn_share_title(note.getCn_note_title());
		shareNote.setCn_share_body(note.getCn_note_body());
		shareNote.setCn_note_id(noteId);
		//ʹ�ù������ȡid
		String id = NoteUtil.createId();
		shareNote.setCn_share_id(id);
		//��������
		shareNoteDao.share(shareNote);
		result.setMsg("����ɹ�");
		result.setStatus(0);
		return result;
	}

}
