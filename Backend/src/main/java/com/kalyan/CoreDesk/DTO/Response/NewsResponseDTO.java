package com.kalyan.CoreDesk.DTO.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsResponseDTO {
    private Long id;
    private String category;
    private String tag;
    private String tagColor;
    private String title;
    private String source;
    private String time;
    private String color;
    private String icon;
}
