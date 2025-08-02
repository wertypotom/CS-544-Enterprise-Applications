package bank.jms;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class KafkaReceiver {
    @Autowired
    private AccountService accountService;

    @KafkaListener(topics = {"bank_topic"})
    public void receive(@Payload String operation) {
        if (operation.equals("createAnAccount")) {
            accountService.createAccount(0000000, "Kevin Durant");
        }
        if (operation.equals("depositMoney")) {
            accountService.deposit(0000000, 240);
        }
        if (operation.equals("withdrawMoney")) {
            accountService.withdraw(0000000, 100);
        }

        Collection<Account> accountlist = accountService.getAllAccounts();
        Customer customer = null;
        for (Account account : accountlist) {
            customer = account.getCustomer();
            System.out.println("Statement for Account: " + account.getAccountnumber());
            System.out.println("Account Holder: " + customer.getName());
            System.out.println("-Date-------------------------"
                    + "-Description------------------"
                    + "-Amount-------------");
            for (AccountEntry entry : account.getEntryList()) {
                System.out.printf("%30s%30s%20.2f\n", entry.getDate()
                        .toString(), entry.getDescription(), entry.getAmount());
            }
            System.out.println("----------------------------------------"
                    + "----------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
                    account.getBalance());
        }
    }
}
