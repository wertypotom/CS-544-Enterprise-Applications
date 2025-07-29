package bank.service;
import bank.domain.Trace;
import bank.integration.EmailSender;
import bank.repositories.TraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.repositories.AccountRepository;
import bank.repositories.CustomerRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class BankService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private TraceService traceService;

	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress, String accountNumber) {
		try {
			performTransaction(customerId, customerName, emailAddress, accountNumber);
			emailSender.sendEmail(emailAddress, "Welcome " + customerName);
			traceService.saveTrace("Customer " + customerName + " created with account " + accountNumber);
		} catch (Exception ex) {
			emailSender.sendEmail(emailAddress, "We could not create your account " + accountNumber);
			traceService.saveTrace("Could not create customer " + customerName + " with account " + accountNumber);
			throw ex;
		}
	}

	@Transactional
	public void performTransaction(int customerId, String customerName, String emailAddress, String AccountNumber){
		Account account = new Account(AccountNumber);
		accountRepository.save(account);
		Customer customer = new Customer(customerId, customerName);
        customer.setAccount(account);
		customerRepository.saveCustomer(customer);
	}

}
