package ToolCalling.profit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfitRepository extends JpaRepository<ProfitEntity, Long> {
    Optional<ProfitEntity> findByMonth(String month);
}
