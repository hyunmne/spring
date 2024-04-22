package com.kosta.shop.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.shop.dto.Cart;
import com.kosta.shop.dto.Goods;
import com.kosta.shop.dto.Member;
import com.kosta.shop.dto.Order;
import com.kosta.shop.dto.OrderInfo;
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
	
	@GetMapping("/orderConfirm")
	public ModelAndView orderConfirm(Goods goods, @RequestParam("gSize") String gSize,
									 @RequestParam("gColor") String gColor, 
									 @RequestParam("gAmount") Integer gAmount) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("gDTO", goods);
		mav.addObject("gSize", gSize);
		mav.addObject("gColor", gColor);
		mav.addObject("gAmount", gAmount);
		mav.setViewName("orderConfirm");
		
		return mav;
	}
	
	@GetMapping("/cartOrderConfirm")
	public ModelAndView cartOrderConfirm(@RequestParam("num") Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			Cart cart = cService.cartRetrive(num);
			mav.addObject("cDTO", cart);
		} catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("orderConfirm");
		return mav;
	}
	
	@GetMapping("/cartOrderAllConfirm")
	public ModelAndView cartOrderAllConfirm(@RequestParam("check") Integer[] check, 
											@RequestParam("cartAmount") Integer[] cartAmount) {
		ModelAndView mav = new ModelAndView();
		
		try {
			List<Cart> cartList = cService.orderAllConfirm(Arrays.asList(check), Arrays.asList(cartAmount));
			for(int i =0; i<cartList.size(); i++) {
				cartList.get(i).setgAmount(cartAmount[i]);
			}
			mav.addObject("cartList", cartList);
		} catch (Exception e) {
			
		}
		mav.setViewName("orderAllConfirm");
		return mav;
	}
	
	@ResponseBody
	@GetMapping("/cartUpdate")
	public void cartUpdate(@RequestParam Map<String,Integer> map) {
		try {
			cService.cartUpdate(map);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@GetMapping("/cartDelete")
	public void cartDelete(@RequestParam Integer num) {
		try {
			cService.cartDelete(num);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@GetMapping("/CartDelAll")
	public String cartDelAll(@RequestParam("check") Integer[] num) {
		try {
			cService.cartDeleteAll(Arrays.asList(num));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/cartList";
	}
	
	@GetMapping("/cartOrderDone")
	public ModelAndView cartOrderDone(@ModelAttribute OrderInfo orderInfo, Order order, 
								@RequestParam(required=false) Integer cartNum) {
		
		ModelAndView mav = new ModelAndView("orderDone");
		Member member = (Member) session.getAttribute("user");
		
		order.setUserId(member.getUserid());
		orderInfo.setUserid(member.getUserid());
		
		try {
			cService.orderDone(orderInfo, order, cartNum);
			mav.addObject("order", order);
			mav.addObject("orderInfo", orderInfo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	
	@GetMapping("/cartOrderAllDone")
	public ModelAndView cartOrderAllDone(@RequestParam("num") Integer[] nums, 
										 @ModelAttribute OrderInfo orderInfo) {
		ModelAndView mav = new ModelAndView("orderAllDone");
		Member member = (Member) session.getAttribute("user");
		orderInfo.setUserid(member.getUserid());
		
		try {
			cService.orderAllDone(orderInfo, Arrays.asList(nums));
			List<Order> orderList = cService.orderList(orderInfo.getNum());
			mav.addObject("orderInfo", orderInfo);
			mav.addObject("orderAllDone", orderList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}
