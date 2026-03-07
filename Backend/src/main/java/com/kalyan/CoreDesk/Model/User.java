package com.kalyan.CoreDesk.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity //for creating table
@Getter//getter function
@Setter//setter function (lombok)
@NoArgsConstructor // default constructor
@AllArgsConstructor //constructor with all fields
@Builder
public class User {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID in database
    private Long Id;

    @Column(unique = false, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Size(min = 6, max = 20, message = "Password must be between 6 to 20 Characters")
    private String password;

    @OneToMany
    private List<Todo> todos;

}
