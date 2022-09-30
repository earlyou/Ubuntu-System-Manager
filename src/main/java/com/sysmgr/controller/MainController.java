package com.sysmgr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sysmgr.biz.UsersBiz;
import com.sysmgr.vo.UsersVO;

@Controller
public class MainController {
	
	@Autowired
	UsersBiz usersbiz;
	
	@RequestMapping("/")
	public String main() {
		return "signin";
	}
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m, String uid, String upwd, HttpSession session) {
		String msg = "";
		UsersVO user = null;
		try {
			user = usersbiz.get(uid);
			if (user != null) {
				if (user.getUpwd().equals(upwd)) {
					session.setAttribute("user", user);
				} else {
					throw new Exception();
				}
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			msg = "아이디와 비밀번호를 확인해주세요.";
			m.addAttribute("fail", msg);
			return "signin";
		}
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(Model m, HttpSession session) {
		String msg = "";
		if (session.getAttribute("user") != null) {
			m.addAttribute("header", "/index/header");
			m.addAttribute("sidebar","/index/sidebar");
			m.addAttribute("main", "/index/main");
			m.addAttribute("footer", "/index/footer");
			return "index/index";
		}else {
			msg = "아이디와 비밀번호를 확인해주세요.";
			m.addAttribute("fail", msg);
			return "signin";
		}
	}
}
