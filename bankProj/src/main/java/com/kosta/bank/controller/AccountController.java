package com.kosta.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@ResponseBody
	@RequestMapping(value = "/accountDoubleId", method = RequestMethod.POST)
	public String accountDoubleIdCheck(String id) {
		try {
			Boolean check = accountService.checkAccountDoubleId(id);
			return String.valueOf(check);
		} catch(Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@RequestMapping(value = "/makeAccount", method = RequestMethod.GET)
	public String makeAccount() {
		return "makeAccount";
	}
	
	@RequestMapping(value = "/makeAccount", method = RequestMethod.POST)
	public String makeAccount(Account acc, Model model) {
		try {
			accountService.makeAccount(acc);
			model.addAttribute("acc", acc);
			return "accountInfo";
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value="/accountInfo", method = RequestMethod.GET)
	public String accountInfo() {
		return "accountInfoForm";
	}
	
	@RequestMapping(value="/accountInfo", method = RequestMethod.POST)
	public ModelAndView accountInfo(@RequestParam("id") String id) {
		// 모델과 뷰를 같이 줄 때
		ModelAndView mav = new ModelAndView();
		
		try {
			Account acc = accountService.accountInfo(id);
			mav.addObject("acc", acc); // 데이터..
			mav.setViewName("accountInfo"); // 이동페이지,.. ?
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping(value="/deposit", method = RequestMethod.GET)
	public String Deposit() {
		return "deposit";
	}
	
	@RequestMapping(value="/deposit", method = RequestMethod.POST)
	public ModelAndView Deposit(@RequestParam("id") String id, Integer money) {
		// 모델과 뷰를 같이 줄 때
		ModelAndView mav = new ModelAndView();
		
		try {
			accountService.deposit(id, money);
			Account acc = accountService.accountInfo(id);
			mav.addObject("acc", acc); // 데이터..
			mav.setViewName("accountInfo"); // 이동페이지,.. ?
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping(value="/withdraw", method = RequestMethod.GET)
	public String Withdraw() {
		return "withdraw";
	}
	
	@RequestMapping(value="/withdraw", method = RequestMethod.POST)
	public ModelAndView Withdraw(@RequestParam("id") String id, Integer money) {
		// 모델과 뷰를 같이 줄 때
		ModelAndView mav = new ModelAndView();
		
		try {
			accountService.withdraw(id, money);
			Account acc = accountService.accountInfo(id);
			mav.addObject("acc", acc); // 데이터..
			mav.setViewName("accountInfo"); // 이동페이지,.. ?
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping(value="/allAccountInfo", method = RequestMethod.GET)
	public String allAccountInfo(Model model) {
		try {
			List<Account> accs = accountService.accList();
			model.addAttribute("accs", accs);
			return "allAccountInfo";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value="/transfer", method = RequestMethod.GET)
	public String Transfer() {
		return "transfer";
	}
	
	@Transactional
	@RequestMapping(value="/transfer", method = RequestMethod.POST)
	public ModelAndView Transfer(String sid, String rid, Integer money) {
		// 모델과 뷰를 같이 줄 때
		ModelAndView mav = new ModelAndView();
		try {
			accountService.transfer(sid, rid, money);
			Account acc = accountService.accountInfo(sid);
			mav.addObject("acc", acc); // 데이터..
			mav.setViewName("accountInfo"); // 이동페이지,.. ?
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}

}
