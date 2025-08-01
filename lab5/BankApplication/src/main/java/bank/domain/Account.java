package bank.domain;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Account {
	@Id
	@GeneratedValue
	private Long id;

	private Long accountnumber;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "acc_id")
	List<AccountEntry> entryList = new ArrayList();

	@Embedded
	Customer customer;

	public Account (){
	}

	public Account (long accountnr){
		this.accountnumber = accountnr;
	}

	public long getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}

	public void deposit(double amount) {
		AccountEntry entry = new AccountEntry(new Date(), amount, "deposit", "", "");
		entryList.add(entry);
	}

	public void withdraw(double amount) {
		AccountEntry entry = new AccountEntry(new Date(), -amount, "withdraw", "", "");
		entryList.add(entry);	
	}

	private void addEntry(AccountEntry entry){
		entryList.add(entry);
	}

	public void transferFunds(Account toAccount, double amount, String description){
		AccountEntry fromEntry = new AccountEntry(new Date(), -amount, description, ""+toAccount.getAccountnumber(), toAccount.getCustomer().getName());
		AccountEntry toEntry = new AccountEntry(new Date(), amount, description, ""+toAccount.getAccountnumber(), toAccount.getCustomer().getName());
		entryList.add(fromEntry);	
		toAccount.addEntry(toEntry);
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<AccountEntry> getEntryList() {
		return entryList;
	}

	@Override
	public String toString() {
		return "Account{" +
				"accountnumber=" + accountnumber +
				", entryList=" + entryList +
				", customer=" + customer +
				'}';
	}
}
