package bank.dao;

import bank.domain.TraceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraceDAO extends JpaRepository<TraceRecord, Long> {
}
