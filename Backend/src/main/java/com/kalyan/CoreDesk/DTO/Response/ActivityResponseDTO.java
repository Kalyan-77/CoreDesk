package com.kalyan.CoreDesk.DTO.Response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponseDTO {
    private Long id;

    private int steps;

    private double distanceKm;

    private double calories;

    private LocalDate date;

    private Long userId;
}
