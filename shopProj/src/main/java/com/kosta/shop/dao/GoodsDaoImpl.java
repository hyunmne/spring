package com.kosta.shop.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.shop.dto.Goods;

@Repository
public class GoodsDaoImpl implements GoodsDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Goods> goodsList() throws Exception {
		return sqlSession.selectList("mapper.goods.selectGoodsList");
	}

	@Override
	public Goods goodsDetail(String gCode) throws Exception {
		return sqlSession.selectOne("mapper.goods.selectGoodsDetail", gCode);
	}
	
}
