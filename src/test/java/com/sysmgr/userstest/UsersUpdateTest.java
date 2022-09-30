package com.sysmgr.userstest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sysmgr.biz.UsersBiz;
import com.sysmgr.vo.UsersVO;

@SpringBootTest
class UsersUpdateTest {

	@Autowired
	UsersBiz biz;
	
	@Test
	void contextLoads() {
		UsersVO user = null;
		try {
			user = biz.get("admin");
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
