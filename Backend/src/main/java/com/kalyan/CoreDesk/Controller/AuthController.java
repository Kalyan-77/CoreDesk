package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.DTO.Request.LoginRequestDTO;
import com.kalyan.CoreDesk.DTO.Request.RegisterRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.UserResponseDTO;
import com.kalyan.CoreDesk.Model.User;
import com.kalyan.CoreDesk.Service.AuthService;
import com.kalyan.CoreDesk.Utils.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public String login(@Valid @RequestBody LoginRequestDTO request, HttpServletRequest httpRequest){
        return authService.login(request,httpRequest);
    }

    @GetMapping("/getUser/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id, HttpSession session){
        SessionUtil.getSessionUserId(session);
        return authService.getUserById(id);
    }

    @GetMapping("/AllUsers")
    public List<UserResponseDTO> getAllUsers(){
        return authService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public String Delete(@PathVariable Long id, HttpSession session){
        SessionUtil.getSessionUserId(session);
        return authService.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user, HttpSession session){
        SessionUtil.getSessionUserId(session);
        return authService.UpdateUser(id,user);
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "Logged Out Successfully..";
    }
}
