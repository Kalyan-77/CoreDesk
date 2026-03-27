package com.kalyan.CoreDesk.Service;

import com.kalyan.CoreDesk.DTO.Request.LoginRequestDTO;
import com.kalyan.CoreDesk.DTO.Request.RegisterRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.UserResponseDTO;
import com.kalyan.CoreDesk.Model.User;
import com.kalyan.CoreDesk.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Automatically creates constructor for final fields (Dependency Injection)
public class AuthService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserResponseDTO register(RegisterRequestDTO request){
        // Check if email already exists
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("User Already Exists with this email. Please use other email to register!!");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);
        return UserResponseDTO.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();
    }

    public String login(LoginRequestDTO request, HttpServletRequest httpRequest){
        User existingUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User Not Found!!"));

        if(!passwordEncoder.matches(request.getPassword(), existingUser.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ Invalidate old session, get fresh one with new ID
//        String oldSessionId = session.getId();
//        session.invalidate();
//
//        session.setAttribute("userId" , existingUser.getId());
//        session.setAttribute("email" , existingUser.getEmail());
//        session.setAttribute("username", existingUser.getUsername());

        //Browser sends HTTP Request
        //↓
        //HttpServletRequest  ← the entire request (headers, body, cookies, URL, method...)
        //↓
        //httpRequest.getSession()  ← extracts session from the request
        //↓
        //HttpSession  ← just the session part (your stored attributes)

        HttpSession oldSession = httpRequest.getSession(false); // ✅ don't create if missing
        if(oldSession != null){
            oldSession.invalidate(); // ✅ destroy old session ID
        }

        // Create brand new session with new ID
        HttpSession newSession = httpRequest.getSession(true);// ✅ create fresh session
        newSession.setAttribute("userId", existingUser.getId());// ✅ store in new session
        newSession.setAttribute("email", existingUser.getEmail());
        newSession.setAttribute("username", existingUser.getUsername());

        return "Login Successful!!";
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserResponseDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));


        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public String deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw  new RuntimeException("User Not Found!!");
        }

        userRepository.deleteById(id);
        return "User Deleted Successful!!";
    }

    public User UpdateUser(Long id, User updatedUser){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not found!!"));

        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

        return userRepository.save(existingUser);
    }
}
