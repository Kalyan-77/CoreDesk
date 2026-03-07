package com.kalyan.CoreDesk.Repository;


import com.kalyan.CoreDesk.Model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findByUserIdAndDate(Long userId, LocalDate date);

    List<Activity> findByUserId(Long userId);
}
