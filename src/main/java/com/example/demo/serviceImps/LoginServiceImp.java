package com.example.demo.serviceImps;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.services.LoginService;
import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;

@Service
public class LoginServiceImp implements LoginService{
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public String checkLoginInfo(String username, String password) {
		Account accountResult = accountRepository.findByUsername(username);
		if (accountResult == null) {
			return "-1";
		} else {
			if (password.equals(accountResult.getPassword())) {
				return String.valueOf(accountResult.getId());
			} else {
				return "-1";
			}
		}
	}
}
