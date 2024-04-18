package com.kosta.shop.dao;

import java.util.List;

import com.kosta.shop.dto.Goods;

public interface GoodsDao {
	List<Goods> goodsList() throws Exception;
	Goods goodsDetail(String gCode) throws Exception;
}
