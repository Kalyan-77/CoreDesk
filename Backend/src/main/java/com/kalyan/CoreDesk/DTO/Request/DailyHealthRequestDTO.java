package com.kalyan.CoreDesk.DTO.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyHealthRequestDTO {
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