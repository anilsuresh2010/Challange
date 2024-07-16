package com.dws.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dws.challenge.domain.Account;
import com.dws.challenge.exception.DuplicateAccountIdException;

import jakarta.persistence.LockModeType;


@Repository
@Transactional(readOnly = true)
public interface AccountsRepository extends JpaRepository<Account, Long>{
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	@Query("SELECT a FROM Account a WHERE a.accountId = ?1")
	public Optional<Account> getAccountInfo(Long id);
	
	void createAccount(Account account) throws DuplicateAccountIdException;

	  Account getAccount(Long accountnumber);
	  
	  Account save(Account acount);

	  void clearAccounts();

}
