package org.wcarrascal.junit5app.examples.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.wcarrascal.junit5app.examples.exceptions.InsufficientBalanceException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void test_name_account(){
        Account account = new Account("Andres", new BigDecimal("10000.2"));

        String expectedResult = "Andres";
        String actualResult = account.getCustomer();

        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult, () -> "Customer is not equals.");
    }

    @Test
    void test_balance_account(){
        Account account = new Account("Andres", new BigDecimal("10000.2"));
        assertEquals(10000.2, account.getBalance().doubleValue());
        assertNotNull(account.getBalance());
        assertFalse(account.getBalance().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void test_referent_account() {
        Account account = new Account("John", new BigDecimal("20000"));
        Account accountAux = new Account("John", new BigDecimal("20000"));

        assertEquals(account, accountAux);
    }

    @Test
    void test_debit_account() {
        Account account = new Account("John", new BigDecimal("20000.50"));
        account.debit(new BigDecimal("1000"));
        assertNotNull(account.getBalance());
        assertEquals(19000, account.getBalance().intValue());
        assertEquals("19000.50", account.getBalance().toPlainString());
    }

    @Test
    void test_credit_account() {
        Account account = new Account("John", new BigDecimal("20000.50"));
        account.credit(new BigDecimal("1000"));
        assertNotNull(account.getBalance());
        assertEquals(21000, account.getBalance().intValue());
        assertEquals("21000.50", account.getBalance().toPlainString());
    }

    @Test
    void test_insufficient_balance_account() {
        Account account = new Account("John", new BigDecimal("20000.50"));
        Exception ex = assertThrows(InsufficientBalanceException.class, ()->{
            account.debit(new BigDecimal("21000"));
        });
        String expectedResult = "Insufficient Balance.";
        String actualResult = ex.getMessage();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_transfer_amount() {
        Account accountJohn = new Account("John", new BigDecimal("20000.50"));
        Account accountAndres = new Account("Andres", new BigDecimal("50000.20"));

        Bank bankCat = new Bank();
        bankCat.setName("Cat lovers");
        bankCat.tranfer(accountJohn, accountAndres, new BigDecimal(100));

        assertEquals("19900.50", accountJohn.getBalance().toPlainString());
        assertEquals("50100.20", accountAndres.getBalance().toPlainString());
    }

    @Test
    void test_transfer_amount_asso_bank() {
        Account accountJohn = new Account("John", new BigDecimal("20000.50"));
        Account accountAndres = new Account("Andres", new BigDecimal("50000.20"));

        Bank bankCat = new Bank();

        bankCat.setName("Cat lovers");
        bankCat.addAccount(accountJohn);
        bankCat.addAccount(accountAndres);

        bankCat.tranfer(accountJohn, accountAndres, new BigDecimal(100));

        assertAll(
                ()-> assertEquals("19900.50", accountJohn.getBalance().toPlainString()),
                ()-> assertEquals("50100.20", accountAndres.getBalance().toPlainString()),
                ()-> assertEquals(2, bankCat.getAccounts().size()),
                ()-> assertEquals("Cat lovers", accountJohn.getBank().getName()),
                ()-> assertEquals("John", bankCat.getAccounts().stream()
                            .filter(c -> c.getCustomer().equals("John"))
                            .findFirst()
                            .get().getCustomer()
                    ),
                ()-> assertTrue(bankCat.getAccounts().stream()
                            .filter(c -> c.getCustomer().equals("John"))
                            .findFirst().isPresent()
                    ),
                ()-> {
                    assertTrue(bankCat.getAccounts().stream().anyMatch(c -> c.getCustomer().equals("John")));
                }
        );
    }
}