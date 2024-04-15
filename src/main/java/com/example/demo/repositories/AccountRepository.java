package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	Account findByUsername(String username);
}
