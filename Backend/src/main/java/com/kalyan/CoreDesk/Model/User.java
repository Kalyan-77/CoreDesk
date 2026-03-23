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
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Todo> todos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Remainder> remainders;

}

//Without `mappedBy`, Hibernate creates a separate `user_todos` join table in your database — completely unnecessary since `todo` table already has `user_id`.
//Cascade means — whatever you do to the User, do the same to its Todos/Activities/Reminders automatically.