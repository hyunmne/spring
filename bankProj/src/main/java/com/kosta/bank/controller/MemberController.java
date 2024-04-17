package com.kosta.bank.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public String Login(String id, String password, String autoLogin, Model model, HttpServletRequest request, HttpServletResponse response) {
		try {

			// 로그인 성공시 cookie check 저저장
			Cookie autoLoginCookie = null;
			Cookie idCookie = null;
			Cookie pwCookie = null;
			if(autoLogin!=null) {
				autoLoginCookie = new Cookie("autoLogin", autoLogin);
				idCookie = new Cookie("id", id);
				pwCookie = new Cookie("password", password);
			} else {
				autoLoginCookie = new Cookie("autoLogin", "false");
				idCookie = new Cookie("id", "");
				pwCookie = new Cookie("password", "");
			}
			response.addCookie(autoLoginCookie);
			response.addCookie(idCookie);
			response.addCookie(pwCookie);

			memService.login(id, password);
			session.setAttribute("user", id);
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
