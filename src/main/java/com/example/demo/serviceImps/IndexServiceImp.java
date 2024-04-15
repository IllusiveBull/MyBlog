package com.example.demo.serviceImps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.services.IndexService;

@Service
public class IndexServiceImp implements IndexService{
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account getAccountById(String accountId) {
		if (accountId == null) {
			return null;
		}
		return accountRepository.getById(Integer.valueOf(accountId));
	}
}
