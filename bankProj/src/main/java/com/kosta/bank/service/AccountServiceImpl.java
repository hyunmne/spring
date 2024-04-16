package com.kosta.bank.service;

import com.kosta.bank.dao.AccountDao;
import com.kosta.bank.dto.Account;

public class AccountServiceImpl implements AccountService {

	private AccountDao accDao;
	
	public void setAccDao(AccountDao accDao) {
		this.accDao = accDao;
	}

	@Override
	public void makeAccount(Account acc) throws Exception {
		accDao.insertAccount(acc);
	}

}
