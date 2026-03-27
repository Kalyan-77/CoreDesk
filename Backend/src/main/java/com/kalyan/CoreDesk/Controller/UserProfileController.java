package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.DTO.Request.UserProfileRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.UserProfileResponseDTO;
import com.kalyan.CoreDesk.Model.User;
import com.kalyan.CoreDesk.Service.UserProfileService;
import com.kalyan.CoreDesk.Utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/profile/{userId}")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping("/create")
    public UserProfileResponseDTO createProfile(@PathVariable Long userId, @RequestBody UserProfileRequestDTO request, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return userProfileService.createProfile(userId, request);
    }

    @GetMapping("/getProfile")
    public UserProfileResponseDTO getProfile(@PathVariable Long userId, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return userProfileService.getProfile(userId);
    }

    @PutMapping("/update")
    public  UserProfileResponseDTO updateProfile(@PathVariable Long userId, @RequestBody UserProfileRequestDTO request, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return userProfileService.updateProfile(userId, request);
    }
}
