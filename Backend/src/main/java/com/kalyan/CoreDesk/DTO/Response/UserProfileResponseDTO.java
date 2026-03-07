package com.kalyan.CoreDesk.DTO.Response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponseDTO {
    private Long id;

    private int age;

    private double height;

    private double weight;

    private String gender;

    private int dailyStepGoal;

    private double dailyCalorieGoal;

    private List<String> goals;

    private Long userId;
}
