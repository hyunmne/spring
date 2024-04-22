package com.kosta.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.CartDao;
import com.kosta.shop.dto.Cart;
import com.kosta.shop.dto.Order;
import com.kosta.shop.dto.OrderInfo;

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
	public List<Cart> orderAllConfirm(List<Integer> cartNum, List<Integer> cartAmt) throws Exception {
		Map<String, Integer> param = new HashMap<>();
		for(int i=0; i<cartNum.size(); i++) {
			param.clear();
			param.put("num", cartNum.get(i));
			param.put("gAmount", cartAmt.get(i));
			cDao.updateCartAmount(param);
		}
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
	public void orderDone(OrderInfo orderInfo, Order order, Integer cartNum) throws Exception {
		cDao.insertOrderInfo(orderInfo);
		order.setOrderinfo_num(orderInfo.getNum());
		cDao.insertOrder(order);
		if(cartNum != null) {
			cDao.deleteCart(cartNum);
		}
	}

	@Override
	public void orderAllDone(OrderInfo orderInfo, List<Integer> nums) throws Exception {
		cDao.insertOrderInfo(orderInfo);
		for(Integer num : nums) {
			Cart cart = cDao.selectCart(num);
			Order order = new Order();
			order.setUserId(cart.getUserid());
			order.setgCode(cart.getgCode());
			order.setgName(cart.getgName());
			order.setgPrice(cart.getgPrice());
			order.setgSize(cart.getgSize());
			order.setgColor(cart.getgColor());
			order.setgAmount(cart.getgAmount());
			order.setgImage(cart.getgImage());
			order.setOrderinfo_num(orderInfo.getNum());
			cDao.insertOrder(order);
			cDao.deleteCart(num);
		}
	}

	@Override
	public List<Order> orderList(Integer orderinfo_num) throws Exception {
		return cDao.selectOrderList(orderinfo_num);
	}

}
