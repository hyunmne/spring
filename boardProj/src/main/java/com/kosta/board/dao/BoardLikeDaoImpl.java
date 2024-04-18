package com.kosta.board.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardLikeDaoImpl implements BoardLikeDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insertBrdLike(String memberId, Integer boardNum) throws Exception {
		Map<String,Object> param = new HashMap<>();
		param.put("memberId", memberId);
		param.put("boardNum", boardNum);
		sqlSession.insert("mapper.boardlike.insertBrdLike", param);
	}

	@Override
	public void deleteBrdLike(String memberId, Integer boardNum) throws Exception {
		Map<String,Object> param = new HashMap<>();
		param.put("memberId", memberId);
		param.put("boardNum", boardNum);
		sqlSession.delete("mapper.boardlike.deleteBrdLike", param);
	}

	@Override
	public Integer selectBrdLike(String memberId, Integer boardNum) throws Exception {
		Map<String,Object> param = new HashMap<>();
		param.put("memberId", memberId);
		param.put("boardNum", boardNum);
		return sqlSession.selectOne("mapper.boardlike.selectBrdLike", param);
	}


}
