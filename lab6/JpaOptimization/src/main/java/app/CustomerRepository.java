package app;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c")
    List<Customer> findAllWithoutAccounts();

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.name = :newName")
    int bulkUpdateNames(@Param("newName") String newName);
}




