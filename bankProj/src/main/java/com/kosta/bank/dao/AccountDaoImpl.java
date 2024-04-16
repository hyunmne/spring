package com.kosta.bank.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kosta.bank.dto.Account;

public class AccountDaoImpl implements AccountDao {

	private SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertAccount(Account acc) throws Exception {
		sqlSession.insert("mapper.account.insertAccount", acc);
	}
	
}
