package com.kalyan.CoreDesk.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;

    private String gender;

    private double height;//cm

    private double weight;//kg

    private int dailyStepGoal;

    private double dailyCalorieGoal;

    @ElementCollection
    private List<String> goals;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
