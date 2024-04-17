package com.kosta.bank.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.bank.dto.Account;

@Repository // 자동 생성 (빈 객체 생성하는 것)
public class AccountDaoImpl implements AccountDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void insertAccount(Account acc) throws Exception {
		sqlSession.insert("mapper.account.insertAccount", acc);
	}

	@Override
	public Account selectAccount(String id) throws Exception {
	    return sqlSession.selectOne("mapper.account.selectAccount", id);
	}

	@Override
	public void updateAccountBalance(String id, Integer balance) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		param.put("balance", balance);
		sqlSession.update("mapper.account.updateAccountBalance", param);
	}

	@Override
	public List<Account> selectAccList() throws Exception {
		return sqlSession.selectList("mapper.account.selectAccList");
	}
	
}
