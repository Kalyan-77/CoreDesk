package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.Model.News;
import com.kalyan.CoreDesk.Repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsRepository newsRepository;

    @GetMapping("/latest")
    public List<News> getLatestNews() {
        return newsRepository.findTop5ByOrderByPublishedAtDesc();
    }
}
