package org.wcarrascal.junit5app.examples.models;

import java.math.BigDecimal;

public class Account {
    private String customer;
    private BigDecimal balance;

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
