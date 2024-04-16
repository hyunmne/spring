package com.kosta.bank.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.bank.dto.Member;
import com.kosta.bank.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String Join() {
		return "join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String Join(Member mem, Model model) {
		try {
			memService.join(mem);
			model.addAttribute("mem", mem);
			return "accountInfo";
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/memberDoubleId", method=RequestMethod.POST)
	public String memberDoubleIdCheck(String id) {
		try {
			Boolean check = memService.doubleMemberCheckId(id);
			return String.valueOf(check);
		} catch(Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String Login() {
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String Login(String id, String password, Model model) {
		try {
			Member mem = memService.login(id, password);
			mem.setPassword("");
			session.setAttribute("user", mem.getId());
			return "makeAccount";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String Logout(Model model) {
		try {
			session.removeAttribute("user");
			return "login";
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
}
