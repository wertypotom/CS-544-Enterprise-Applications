package bank.dao;

import java.util.*;

import bank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<Account, Long> {
	public default void saveAccount(Account account) {
		save(account);
	}

	public default void updateAccount(Account account) {
		save(account);
	}

	public Account findByAccountnumber(long accountnumber);

	public default Account loadAccount(long accountnumber) {
		return findByAccountnumber(accountnumber);
	};

	public default Collection<Account> getAccounts() {
		return findAll();
	}

}
