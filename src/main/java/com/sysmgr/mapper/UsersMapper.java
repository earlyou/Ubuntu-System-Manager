package com.sysmgr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sysmgr.vo.UsersVO;

@Repository
@Mapper
public interface UsersMapper {
	public void insert(UsersVO obj) throws Exception;
	public void update(UsersVO obj) throws Exception;
	public void delete(String uid) throws Exception;
	public UsersVO select(String uid) throws Exception;
	public List<UsersVO> selectall() throws Exception;
}
