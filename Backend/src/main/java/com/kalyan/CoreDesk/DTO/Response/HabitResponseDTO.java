package com.kalyan.CoreDesk.DTO.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitResponseDTO {
    private Long id;
    private String label;
    private String icon;
    private String color;
    private int streak;
    private boolean done;
}
