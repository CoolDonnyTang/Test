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
	//注入
	@Resource
	NoteDao noteDao;
	@Resource
	ShareNoteDao shareNoteDao;
	
	public NoteResult shareNote(String noteId) {
		NoteResult result = new NoteResult();
		ShareNote shareNote = new ShareNote();
		if(null == noteId||"".equals(noteId)) {
			result.setMsg("请选择要分享的笔记 ");
			result.setStatus(1);
			return result;
		}
		//根据noteId查询被分享的笔记的标题和内容
		Note note = noteDao.findByNoteId(noteId);
		///如果未找到相应笔记则返回相应提示
		if(note == null) {
			result.setMsg("分享失败 要分享的笔记不存在");
			result.setStatus(2);
			return result;
		}
		/*
		 * 判断该条分享在分享表中是否存在
		 */
		//使用noteId在分享表中查询
		String title = shareNoteDao.findByNoteId(noteId);
//System.out.println(title);
		if(!(title==null&&"".equals(title))) {
			/*
			 * 满足条件说明该条分享存在，只需要update（更新）即可
			 * 更严谨则应该判断标题和内容是否改变，若改变了再update（更新）
			 * 在此为节约时间我就采取不判断直接执行更新
			 */
			shareNote.setCn_share_title(note.getCn_note_title());
			shareNote.setCn_share_body(note.getCn_note_body());
			shareNote.setCn_note_id(noteId);
			shareNoteDao.updateShare(shareNote);
			//不再执行后面
			result.setMsg("更新分享成功");
			result.setStatus(0);
			return result;
		}
		/*
		 * 若没该条笔记没有被分享过则创建分享
		 */
		//将找到的信息传给shareNote的对象
		shareNote.setCn_share_title(note.getCn_note_title());
		shareNote.setCn_share_body(note.getCn_note_body());
		shareNote.setCn_note_id(noteId);
		//使用工具类获取id
		String id = NoteUtil.createId();
		shareNote.setCn_share_id(id);
		//插入数据
		shareNoteDao.share(shareNote);
		result.setMsg("分享成功");
		result.setStatus(0);
		return result;
	}

}
