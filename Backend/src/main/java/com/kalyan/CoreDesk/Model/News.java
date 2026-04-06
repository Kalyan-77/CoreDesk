package com.kalyan.CoreDesk.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String tag;
    private String tagColor;
    private String title;
    private String source;
    private String time; // e.g., "12m", "1hr"
    private LocalDateTime publishedAt;
    private String color;
    private String icon;
}
