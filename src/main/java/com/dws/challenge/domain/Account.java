package com.dws.challenge.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "ACCOUNT")
public class Account {

  
  @Id
  @Column(name = "ACCOUNTID")
  private final Long accountId;

  @NotNull
  @Column(name = "BALANCE")
  @Min(value = 0, message = "account balance must be positive")
  private BigDecimal balance;

  public Account(Long accountId) {
    this.accountId = accountId;
    this.balance = BigDecimal.ZERO;
  }

  @JsonCreator
  public Account(@JsonProperty("accountId") Long accountId,
    @JsonProperty("balance") BigDecimal balance) {
	    this.accountId = accountId;
	    this.balance = balance;
  }

	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public Long getAccountId() {
		return accountId;
	}

}
