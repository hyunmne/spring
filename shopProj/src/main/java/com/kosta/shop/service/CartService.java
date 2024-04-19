package com.kosta.shop.service;

import java.util.List;
import java.util.Map;

import com.kosta.shop.dto.Cart;
import com.kosta.shop.dto.Order;

public interface CartService {
	void addCart(Cart cart) throws Exception;
	List<Cart> cartList(String userid) throws Exception;
	Cart cartRetrive(Integer num) throws Exception;
	List<Cart> orderAllConfirm(List<Integer> cartNum) throws Exception;
	void cartUpdate(Map<String,Integer> param) throws Exception;
	void cartDelete(Integer num) throws Exception;
	void cartDeleteAll(List<Integer> nums) throws Exception;
	void orderDone(Order order, Integer cartNum) throws Exception;
}
