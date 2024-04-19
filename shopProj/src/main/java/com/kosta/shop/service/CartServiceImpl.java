package com.kosta.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.CartDao;
import com.kosta.shop.dto.Cart;

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

}
