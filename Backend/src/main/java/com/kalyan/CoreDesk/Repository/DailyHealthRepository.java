package com.kalyan.CoreDesk.Repository;

import com.kalyan.CoreDesk.Model.DailyHealth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyHealthRepository extends JpaRepository<DailyHealth, Long> {
    Optional<DailyHealth> findByUserIdAndDate(Long userId, LocalDate date);
}
