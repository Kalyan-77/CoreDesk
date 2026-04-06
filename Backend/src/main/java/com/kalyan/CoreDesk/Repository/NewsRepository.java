package com.kalyan.CoreDesk.Repository;

import com.kalyan.CoreDesk.Model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findTop5ByOrderByPublishedAtDesc();
}
