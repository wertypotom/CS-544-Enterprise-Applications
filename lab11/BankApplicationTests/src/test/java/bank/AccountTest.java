package bank;

import bank.domain.Account;
import bank.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class AccountTest {
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account(1234567);
    }

    @Test
    public void testIncrement(){
        account.deposit(100.0);
        assertThat( account.getBalance(), closeTo (100.0, 0.01));
    }

    @Test
    public void testDecrement(){
        account.withdraw(100.0);
        assertThat( account.getBalance(), closeTo (-100.0, 0.01));
    }

    @Test
    public void testTransfer(){
        account.deposit(100.0);
        Account toAccount = new Account(1224567);
        toAccount.setCustomer(new Customer());
        account.transferFunds(toAccount, 30.0, "For snacks");
        assertThat(account.getBalance(), closeTo (70.0, 0.01));
        assertThat(toAccount.getBalance(), closeTo (30.0, 0.01));
    }
}