package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.DTO.Request.LoginRequestDTO;
import com.kalyan.CoreDesk.DTO.Request.RegisterRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.UserResponseDTO;
import com.kalyan.CoreDesk.Model.User;
import com.kalyan.CoreDesk.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks class as REST API controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody RegisterRequestDTO request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequestDTO request){
        return authService.login(request);
    }

    @GetMapping("/getUser/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id){
        return authService.getUserById(id);
    }

    @GetMapping("/AllUsers")
    public List<User> getAllUsers(){
        return authService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public String Delete(@PathVariable Long id){
        return authService.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return authService.UpdateUser(id,user);
    }
}
