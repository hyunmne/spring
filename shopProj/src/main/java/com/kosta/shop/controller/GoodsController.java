package com.kosta.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.shop.dto.Cart;
import com.kosta.shop.dto.Goods;
import com.kosta.shop.dto.Member;
import com.kosta.shop.service.CartService;
import com.kosta.shop.service.GoodsService;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService gService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CartService cService;
	
	@GetMapping("/goodsRetrieve")
	public ModelAndView goodsDetail(@RequestParam("gCode")String gCode) {
		ModelAndView mav = new ModelAndView();
		
		try {
			Goods good = gService.goodsDetail(gCode);
			mav.addObject("item", good);
			mav.setViewName("goodsRetrieve");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("action", "상품 조회");
			mav.addObject("message", "상품 상세 조회 실패");
			mav.setViewName("memberResult");
		}
		
		return mav;
	}
	
//	@GetMapping("/goodsRetrieve")
//	@ModelAttribute("item")
//	public Goods goodsDetail(@RequestParam("gCode")String gCode) {
//		Goods good = null;
//		try {
//			good = gService.goodsDetail(gCode);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return good;
//	}
	
	@GetMapping("/addCart")
	public String addCart(Cart cart) {
		Member mem = (Member)session.getAttribute("user");
		cart.setUserid(mem.getUserid());
		
		try {
			cService.addCart(cart);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/goodsRetrieve?gCode="+cart.getgCode();
	}
	
	@GetMapping("/cartList")
	@ModelAttribute("cartList")
	public List<Cart> cartList(String userid) {
		List<Cart> carts = null;
		try {
			Member mem = (Member) session.getAttribute("user");
			carts = cService.cartList(mem.getUserid());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return carts;
	}
	
}
