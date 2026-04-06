package com.kalyan.CoreDesk.DTO.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitRequestDTO {
    private String label;
    private String icon;
    private String color;
}
