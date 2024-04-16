package com.kosta.bank.service;

import java.util.List;

import com.kosta.bank.dao.AccountDao;
import com.kosta.bank.dto.Account;

public class AccountServiceImpl implements AccountService {

	private AccountDao accDao;
	
	public void setAccDao(AccountDao accDao) {
		this.accDao = accDao;
	}

	@Override
	public void makeAccount(Account acc) throws Exception {
		if (accDao.selectAccount(acc.getId())!=null) {
			throw new Exception("계좌번호 중복 오류");
		};
		accDao.insertAccount(acc);
	}

	@Override
	public Account accountInfo(String id) throws Exception {
		Account acc = accDao.selectAccount(id);
		if (acc == null) throw new Exception("계좌번호 오류");
		return acc;
	}

	@Override
	public void deposit(String id, Integer money) throws Exception {
		Account acc = accDao.selectAccount(id);
		if(acc==null) throw new Exception("계좌번호 오류");
		acc.deposit(money);
		accDao.updateAccountBalance(id, acc.getBalance());
	}

	@Override
	public void withdraw(String id, Integer money) throws Exception {
		Account acc = accDao.selectAccount(id);
		if(acc==null) throw new Exception("계좌번호 오류");
		acc.withdraw(money);
		accDao.updateAccountBalance(id, acc.getBalance());
	}

	@Override
	public List<Account> accList() throws Exception {
		return accDao.selectAccList();
	}

}
