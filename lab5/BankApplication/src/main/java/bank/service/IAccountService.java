package bank.service;

import java.util.Collection;
import java.util.List;

import bank.domain.Account;
import bank.service.dto.AccountDTO;


public interface IAccountService {
    public Account createAccount(long accountNumber, String customerName);
    public Account getAccount(long accountNumber);
    public List<AccountDTO> getAllAccounts();
    public void deposit (long accountNumber, double amount);
    public void withdraw (long accountNumber, double amount);
    public void depositEuros (long accountNumber, double amount);
    public void withdrawEuros (long accountNumber, double amount);
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description);
}
