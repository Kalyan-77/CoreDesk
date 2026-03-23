package com.kalyan.CoreDesk.DTO.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoRequestDTO {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private String priority;
    private String category;

}
