package com.example.demo.serviceImps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.services.RegisterService;

@Service
public class RegisterServiceImp implements RegisterService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public boolean Register(String username, String password) {
		if (accountRepository.findByUsername(username) != null) {
			return false;
		} else if (username.isEmpty() || password.isEmpty()) {
			return false;
		} else {
			Account account = new Account();
			account.setUsername(username);
			account.setPassword(password);
			accountRepository.save(account);
			return true;
		}
		
	}

}
