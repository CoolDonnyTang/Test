package com.tyr.note.Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tyr.note.dao.UserDao;
import com.tyr.note.entity.NoteResult;
import com.tyr.note.entity.User;
import com.tyr.note.util.NoteUtil;

@Service //ɨ��Service���
public class UserServiceImpl implements UserService {
	
	//ע��ʹ��
	@Resource
	UserDao dao;
	
	public NoteResult checkLogin(String name, String pwd) throws Exception {
		//״̬��Ϣ����
		NoteResult result = new NoteResult();
		User user = dao.findByName(name);
		if(null == user) {
			//˵���û������ڣ�������Ӧ��״̬��Ϣ
			result.setStatus(1);
			result.setMsg("�û���������");
			return result;
		}
		//���û���Ӧ������ʹ��MD5����
		String md5_pwd = NoteUtil.md5(pwd);
		if(!user.getCn_user_pwd().equals(md5_pwd)) {
			result.setStatus(2);
			result.setMsg("���벻��ȷ");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��֤��¼��Ϣ�ɹ�");
		result.setData(user.getCn_user_id());
		return result;
	}

	public NoteResult regist(String name, String pwd, String nickname) throws Exception {
System.out.println(name);
System.out.println(pwd);
System.out.println(nickname);
		//�������ض���
		NoteResult result = new NoteResult();
		//����û����Ƿ�ռ��
		User hasUser = dao.findByName(name);
		if(null != hasUser) {
			result.setStatus(1);
			result.setMsg("�û�����ռ��");
			return result;
		}
		User user = new User();
		user.setCn_user_name(name);
		user.setCn_user_desc(nickname);
		//��������
		String md5_pwd = NoteUtil.md5(pwd);
		user.setCn_user_pwd(md5_pwd);
		//ʹ�ù������ȡid
		String userId = NoteUtil.createId();
		System.out.println(userId);
		user.setCn_user_id(userId);
		//����UserDao�������
		dao.save(user);
		//����״̬��Ϣ
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		return result;
	}

}
