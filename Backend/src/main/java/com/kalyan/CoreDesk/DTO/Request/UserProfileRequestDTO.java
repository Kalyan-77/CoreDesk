package com.kalyan.CoreDesk.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRequestDTO {

    private int age;

    private double height;

    private double weight;

    private String gender;

    private int dailyStepGoal;

    private double dailyCalorieGoal;

    private List<String> goals;
}
