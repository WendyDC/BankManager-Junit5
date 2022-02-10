package org.wcarrascal.junit5app.examples.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void test_name_account(){
        Account account = new Account("Andres", new BigDecimal("10000.2"));

        String expectedResult = "Andres";
        String actualResult = account.getCustomer();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_balance_account(){
        Account account = new Account("Andres", new BigDecimal("10000.2"));
        assertEquals(10000.2, account.getBalance().doubleValue());
        assertFalse(account.getBalance().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void test_referent_account() {
        Account account = new Account("John", new BigDecimal("20000"));
        Account accountAux = new Account("John", new BigDecimal("20000"));
        
        assertEquals(account, accountAux);
    }
}