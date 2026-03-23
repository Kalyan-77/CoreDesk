package com.kalyan.CoreDesk.Repository;

import com.kalyan.CoreDesk.Model.Remainder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemainderRepository extends JpaRepository<Remainder, Long> {

    List<Remainder> findByUserId(Long userId);
}
