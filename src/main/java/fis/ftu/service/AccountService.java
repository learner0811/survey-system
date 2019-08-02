package fis.ftu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.ftu.dao.AccountDao;
import fis.ftu.model.Account;

@Service
public class AccountService {
	@Autowired
	private AccountDao accountDao;
	
	public Account findAccountByUserName(String username) {
		return accountDao.findByUserName(username); 
	}
}
