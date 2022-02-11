package org.wcarrascal.junit5app.examples.models;

import org.wcarrascal.junit5app.examples.exceptions.InsufficientBalanceException;

import java.math.BigDecimal;

public class Account {
    private String customer;
    private BigDecimal balance;
    private Bank bank;

    public Account(String customer, BigDecimal balance) {
        this.customer = customer;
        this.balance = balance;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void debit(BigDecimal amount){
        BigDecimal newBalance= this.balance.subtract(amount);
        if(newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new InsufficientBalanceException("Insufficient Balance.");
        }
        this.balance = newBalance;
    }

    public void credit(BigDecimal amount){
        this.balance= this.balance.add(amount);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Account)) {
            return false;
        }

        Account account =(Account)obj ;
        if(this.customer == null || this.balance == null){
            return false;
        }
        return this.customer.equals(account.getCustomer())&&this.balance.equals(account.getBalance());
    }
}
