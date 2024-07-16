package com.dws.challenge.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dws.challenge.domain.Account;
import com.dws.challenge.domain.TransferRequest;
import com.dws.challenge.exception.AccountNotExistException;
import com.dws.challenge.exception.InSufficientBalanceException;
import com.dws.challenge.repository.AccountsRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountsService {
	
	@Autowired
	AccountsRepository repository;
	
	@Autowired
	NotificationService notificationService;
	
	
	public Account createAccount(Account account) {
		return repository.save(account);
	}
	
	public Account getAccount(Long accountNumber) {
		return repository.getAccount(accountNumber);
	}	
	
	 @Transactional
	 public synchronized void transfer(TransferRequest transferRequest){
		 
		 	Account accountFrom = repository.getAccountInfo(transferRequest.getAccountFromId())
					.orElseThrow(() -> new AccountNotExistException("Account :" + transferRequest.getAccountFromId() + " does not exist."));
			
			Account accountTo = repository.getAccountInfo(transferRequest.getAccountToId())
					.orElseThrow(() -> new AccountNotExistException("Account :" + transferRequest.getAccountFromId() + " does not exist."));
		 
		 	if (accountFrom.getBalance().compareTo(transferRequest.getAmount())<0) {
		 		throw new InSufficientBalanceException("InSufficient balance !!"+transferRequest.getAccountFromId());
		 	}
		 	
		 	accountFrom.setBalance(accountFrom.getBalance().subtract(transferRequest.getAmount()));
		 	accountTo.setBalance(accountTo.getBalance().add(transferRequest.getAmount()));
		 	repository.save(accountFrom);
		 	repository.save(accountTo);
		 				
			notificationService.notifyAboutTransfer(accountFrom.getAccountId(),"Balance debited "+transferRequest.getAmount());
			notificationService.notifyAboutTransfer(accountTo.getAccountId(), "Balace Credite " +transferRequest.getAmount()); 
	 }

	public AccountsRepository getRepository() {
		return repository;
	}

	public void setRepository(AccountsRepository repository) {
		this.repository = repository;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

}

