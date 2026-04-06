package com.kalyan.CoreDesk.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyHealth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate date;

    private int waterIntake; // in ml
    private double sleepHours;
    private String sleepQuality;
    private int steps;
    private double calories;
    private double distance; // in km
    private int heartRate; // bpm
    private String mood;
    private int activeMinutes;
    private String stressLevel;
}
