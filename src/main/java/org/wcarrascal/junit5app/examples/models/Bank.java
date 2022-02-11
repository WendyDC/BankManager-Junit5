package org.wcarrascal.junit5app.examples.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    private String name;
    private List<Account> accounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account){
        account.setBank(this);
        accounts.add(account);
    }

    public void tranfer(Account origin, Account end, BigDecimal amount){
        origin.debit(amount);
        end.credit(amount);
    }
}
