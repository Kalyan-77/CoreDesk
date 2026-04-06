package com.kalyan.CoreDesk.DTO.Response;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyHealthResponseDTO {
    private Long id;
    private LocalDate date;
    private int waterIntake;
    private double sleepHours;
    private String sleepQuality;
    private int steps;
    private double calories;
    private double distance;
    private int heartRate;
    private String mood;
    private int activeMinutes;
    private String stressLevel;
}
