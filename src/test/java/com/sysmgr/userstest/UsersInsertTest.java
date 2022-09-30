package com.sysmgr.userstest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sysmgr.biz.UsersBiz;
import com.sysmgr.vo.UsersVO;

@SpringBootTest
class UsersInsertTest {

	@Autowired
	UsersBiz biz;
	
	@Test
	void contextLoads() {
		UsersVO user = new UsersVO("admin1", "admin222", "ear");
		try {
			biz.register(user);
			System.out.println("A admin user registered!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
