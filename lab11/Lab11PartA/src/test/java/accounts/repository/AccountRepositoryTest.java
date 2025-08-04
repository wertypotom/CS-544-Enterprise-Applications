package accounts.repository;

import accounts.domain.Account;
import accounts.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void whenFindByAccountName_thenReturnAccount() {
        Account acc = new Account("123456", 30.00, "Andrey");
        entityManager.persist(acc);
        entityManager.flush();

        Account accFound = accountRepository.findByAccountHolder(acc.getAccountHolder());

        assertThat(accFound.getAccountHolder(), equalTo(acc.getAccountHolder()));
    }
}
