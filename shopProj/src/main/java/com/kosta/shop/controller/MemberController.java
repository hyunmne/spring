package com.kosta.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.shop.dto.Member;
import com.kosta.shop.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/signUp")
	public String signUp() {
		return "signUpForm";
	}
	
	@PostMapping("/signUp")
	public String signUp(Member member, Model model) {
		model.addAttribute("action", "회원가입");
		try {
			memService.signUp(member);
			model.addAttribute("message", "회원가입 성공");
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "회원가입 실패");
		}
		
		return "memberResult";
	}
	
	@ResponseBody
	@GetMapping(value = "/idCheck", produces = "text/plain;charset=UTF-8")
	public String idCheck(@RequestParam("id") String userid, @RequestParam("pw") String pw) {
		try {
			if (memService.idCheck(userid)) {
				return "사용불가능";
			} else {
				return "사용가능";
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "사용불가능";
		}
	}
	
	@GetMapping("/mypage")
	public String myPage() {
		return "mypage";
	}
	
	@PostMapping("/updateMember") 
	public String updateMember(@ModelAttribute Member member, Model model) {
		model.addAttribute("action", "회원 정보 수정");
		try {
			memService.modifyMyPage(member);
			session.setAttribute("user", memService.myPage(member.getUserid()));
			model.addAttribute("message", "회원 정보 수정 성공");
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "회원 정보 수정 실패");
		}
		return "memberResult";
	}
	
}
