package com.kalyan.CoreDesk.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Remainder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate remainderDate;

    private LocalTime remainderTime;

    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
