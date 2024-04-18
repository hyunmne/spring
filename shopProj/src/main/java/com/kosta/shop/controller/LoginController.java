package com.kosta.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.shop.dto.Member;
import com.kosta.shop.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberService memService;
	
	@PostMapping("/login")
	public ModelAndView login(@RequestParam Map<String, String> map) {
		ModelAndView mav = new ModelAndView();
		
		try {
			Member mem = memService.login(map.get("userid"), map.get("passwd"));
			if (mem==null) {
				throw new Exception();
			} else {
				session.setAttribute("user", mem);
				mav.setViewName("redirect:/main");
			}
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("action", "로그인");
			mav.addObject("message", "로그인 실패");
			mav.setViewName("memberResult");
		}
		
		return mav;
	}
	
}
