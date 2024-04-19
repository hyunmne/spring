package com.kosta.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.CartDao;
import com.kosta.shop.dto.Cart;
import com.kosta.shop.dto.Order;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDao cDao;

	@Override
	public void addCart(Cart cart) throws Exception {
		cDao.insertCart(cart);
	}

	@Override
	public List<Cart> cartList(String userid) throws Exception {
		return cDao.selectCartList(userid);
	}

	@Override
	public Cart cartRetrive(Integer num) throws Exception {
		return cDao.selectCart(num);
	}

	@Override
	public List<Cart> orderAllConfirm(List<Integer> cartNum) throws Exception {
		return cDao.selectCheckedCart(cartNum);
	}

	@Override
	public void cartUpdate(Map<String, Integer> param) throws Exception {
		cDao.updateCartAmount(param);
	}

	@Override
	public void cartDelete(Integer num) throws Exception {
		cDao.deleteCart(num);
	}

	@Override
	public void cartDeleteAll(List<Integer> nums) throws Exception {
		for(Integer num : nums) {
			cDao.deleteCart(num);
		}
	}

	@Override
	public void orderDone(Order order, Integer cartNum) throws Exception {
		cDao.insertOrder(order);
		if(cartNum != null) {
			cDao.deleteCart(cartNum);
		}
	}

}
