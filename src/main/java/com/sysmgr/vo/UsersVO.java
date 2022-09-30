package com.sysmgr.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class UsersVO {

	// Fields
	private String uid;
	private String upwd;
	private String uname;
	private Date regdate;
	private Date cfmdate;
	
	// Constructors
	public UsersVO(String uid, String upwd, String uname) {
		this.uid = uid;
		this.upwd = upwd;
		this.uname = uname;
	}
}
