package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.DTO.Request.UserProfileRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.UserProfileResponseDTO;
import com.kalyan.CoreDesk.Model.User;
import com.kalyan.CoreDesk.Service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/{userId}")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping("/create")
    public UserProfileResponseDTO createProfile(@PathVariable Long userId, @RequestBody UserProfileRequestDTO request){
        return userProfileService.createProfile(userId, request);
    }

    @GetMapping("/getProfile")
    public UserProfileResponseDTO getProfile(@PathVariable Long userId){
        return userProfileService.getProfile(userId);
    }

    @PutMapping("/update")
    public  UserProfileResponseDTO updateProfile(@PathVariable Long userId, @RequestBody UserProfileRequestDTO request){
        return userProfileService.updateProfile(userId, request);
    }
}
