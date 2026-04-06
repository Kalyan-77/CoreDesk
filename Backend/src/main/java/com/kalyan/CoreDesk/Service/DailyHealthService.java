package com.kalyan.CoreDesk.Service;

import com.kalyan.CoreDesk.DTO.Request.DailyHealthRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.DailyHealthResponseDTO;
import com.kalyan.CoreDesk.Model.DailyHealth;
import com.kalyan.CoreDesk.Model.User;
import com.kalyan.CoreDesk.Repository.DailyHealthRepository;
import com.kalyan.CoreDesk.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DailyHealthService {
    private final DailyHealthRepository dailyHealthRepository;
    private final UserRepository userRepository;

    public DailyHealthResponseDTO getHealthForToday(Long userId) {
        DailyHealth health = dailyHealthRepository.findByUserIdAndDate(userId, LocalDate.now())
                .orElseGet(() -> saveNewDailyHealth(userId, LocalDate.now()));
        return mapToResponseDTO(health);
    }

    public DailyHealthResponseDTO updateHealth(Long userId, DailyHealthRequestDTO requestDTO) {
        DailyHealth health = dailyHealthRepository.findByUserIdAndDate(userId, LocalDate.now())
                .orElseGet(() -> createDefaultHealth(userId));

        health.setWaterIntake(requestDTO.getWaterIntake());
        health.setSleepHours(requestDTO.getSleepHours());
        health.setSleepQuality(requestDTO.getSleepQuality());
        health.setSteps(requestDTO.getSteps());
        health.setCalories(requestDTO.getCalories());
        health.setDistance(requestDTO.getDistance());
        health.setHeartRate(requestDTO.getHeartRate());
        health.setMood(requestDTO.getMood());
        health.setActiveMinutes(requestDTO.getActiveMinutes());
        health.setStressLevel(requestDTO.getStressLevel());

        return mapToResponseDTO(dailyHealthRepository.save(health));
    }

    private DailyHealth createDefaultHealth(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return DailyHealth.builder()
                .user(user)
                .date(LocalDate.now())
                .build();
    }

    private DailyHealth saveNewDailyHealth(Long userId, LocalDate date) {
        User user = userRepository.findById(userId).orElseThrow();
        DailyHealth health = DailyHealth.builder()
                .user(user)
                .date(date)
                .build();
        return dailyHealthRepository.save(health);
    }

    private DailyHealthResponseDTO mapToResponseDTO(DailyHealth health) {
        return DailyHealthResponseDTO.builder()
                .id(health.getId())
                .date(health.getDate())
                .waterIntake(health.getWaterIntake())
                .sleepHours(health.getSleepHours())
                .sleepQuality(health.getSleepQuality())
                .steps(health.getSteps())
                .calories(health.getCalories())
                .distance(health.getDistance())
                .heartRate(health.getHeartRate())
                .mood(health.getMood())
                .activeMinutes(health.getActiveMinutes())
                .stressLevel(health.getStressLevel())
                .build();
    }
}
