package com.sysmgr.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysmgr.frame.Biz;
import com.sysmgr.mapper.UsersMapper;
import com.sysmgr.vo.UsersVO;

@Service
public class UsersBiz implements Biz<String, UsersVO> {
	
	@Autowired
	UsersMapper usersmapperdao;

	@Override
	public void register(UsersVO v) throws Exception {
		usersmapperdao.insert(v);
	}

	@Override
	public void modify(UsersVO V) throws Exception {
		usersmapperdao.update(V);		
	}

	@Override
	public void remove(String k) throws Exception {
		usersmapperdao.delete(k);
	}

	@Override
	public UsersVO get(String k) throws Exception {
		return usersmapperdao.select(k);
	}

	@Override
	public List<UsersVO> get() throws Exception {
		return usersmapperdao.selectall();
	}
	
	
}
