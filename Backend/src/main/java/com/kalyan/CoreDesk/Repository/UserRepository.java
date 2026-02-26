package com.kalyan.CoreDesk.Repository;

import com.kalyan.CoreDesk.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository give the methods like findAll, findBy etc.
//Why Optional?
//      Because user may or may not exist.
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    // Spring automatically generates query:
    // SELECT * FROM user WHERE username = ?

    Optional<User> findByEmail(String email);
}
