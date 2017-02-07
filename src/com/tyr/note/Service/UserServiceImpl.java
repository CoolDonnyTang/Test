package com.tyr.note.Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tyr.note.dao.UserDao;
import com.tyr.note.entity.NoteResult;
import com.tyr.note.entity.User;
import com.tyr.note.util.NoteUtil;

@Service //扫描Service组件
public class UserServiceImpl implements UserService {
	
	//注入使用
	@Resource
	UserDao dao;
	
	public NoteResult checkLogin(String name, String pwd) throws Exception {
		//状态信息对象
		NoteResult result = new NoteResult();
		User user = dao.findByName(name);
		if(null == user) {
			//说明用户不存在，返回相应的状态信息
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		//将用户对应的密码使用MD5加密
		String md5_pwd = NoteUtil.md5(pwd);
		if(!user.getCn_user_pwd().equals(md5_pwd)) {
			result.setStatus(2);
			result.setMsg("密码不正确");
			return result;
		}
		result.setStatus(0);
		result.setMsg("验证登录信息成功");
		result.setData(user.getCn_user_id());
		return result;
	}

	public NoteResult regist(String name, String pwd, String nickname) throws Exception {
System.out.println(name);
System.out.println(pwd);
System.out.println(nickname);
		//创建返回对象
		NoteResult result = new NoteResult();
		//检查用户名是否被占用
		User hasUser = dao.findByName(name);
		if(null != hasUser) {
			result.setStatus(1);
			result.setMsg("用户名被占用");
			return result;
		}
		User user = new User();
		user.setCn_user_name(name);
		user.setCn_user_desc(nickname);
		//加密密码
		String md5_pwd = NoteUtil.md5(pwd);
		user.setCn_user_pwd(md5_pwd);
		//使用工具类获取id
		String userId = NoteUtil.createId();
		System.out.println(userId);
		user.setCn_user_id(userId);
		//调用UserDao保存对象
		dao.save(user);
		//设置状态信息
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
	}

}
