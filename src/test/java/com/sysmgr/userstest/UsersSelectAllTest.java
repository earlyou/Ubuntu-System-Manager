package com.sysmgr.userstest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sysmgr.biz.UsersBiz;
import com.sysmgr.vo.UsersVO;

@SpringBootTest
class UsersSelectAllTest {

	@Autowired
	UsersBiz biz;
	
	@Test
	void contextLoads() {
		List<UsersVO> list = null;
		try {
			biz.remove("admin1");
			list = biz.get();
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
