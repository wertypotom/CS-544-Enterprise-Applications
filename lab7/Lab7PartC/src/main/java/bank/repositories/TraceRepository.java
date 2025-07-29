package bank.repositories;

import bank.domain.Trace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraceRepository extends JpaRepository<Trace, Long> {
}
