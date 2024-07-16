package com.dws.challenge.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dws.challenge.domain.Account;
import com.dws.challenge.domain.TransferRequest;
import com.dws.challenge.exception.DuplicateAccountIdException;
import com.dws.challenge.exception.InSufficientBalanceException;
import com.dws.challenge.exception.TransactionFailedException;
import com.dws.challenge.service.AccountsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/accounts")
public class AccountsController {
	
	@Autowired
	 private AccountsService accountsService;

	  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Object> createAccount(@RequestBody @Valid Account account) {
	    try {	    	
	    	accountsService.createAccount(account);	    	
	    } catch (DuplicateAccountIdException daie) {
	      return new ResponseEntity<>(daie.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    return new ResponseEntity<>(HttpStatus.CREATED);
	  }
	 
	  //Transfer initiated
	  @PostMapping(path = ("/transfer"), produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public synchronized ResponseEntity<Object> transferAmount(@RequestBody @Valid TransferRequest request) {
	    try {
	    	accountsService.transfer(request);
	    } catch (InSufficientBalanceException ex) {
	    	throw new InSufficientBalanceException("Balance is not sufficient!!");
	    
		}catch (Exception e) {
			throw new TransactionFailedException("Transaction Failed!!");
		}
	    return new ResponseEntity<>(HttpStatus.OK);
	  }
}
