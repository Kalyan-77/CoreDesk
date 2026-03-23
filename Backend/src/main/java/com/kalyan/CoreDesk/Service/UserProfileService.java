package com.kalyan.CoreDesk.Service;

import com.kalyan.CoreDesk.DTO.Request.UserProfileRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.UserProfileResponseDTO;
import com.kalyan.CoreDesk.Model.User;
import com.kalyan.CoreDesk.Model.UserProfile;
import com.kalyan.CoreDesk.Repository.UserProfileRepository;
import com.kalyan.CoreDesk.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public UserProfileResponseDTO createProfile(Long userId, UserProfileRequestDTO request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not found"));

        UserProfile profile = userProfileRepository.findByUserId(userId).orElse(new UserProfile());

        profile.setAge(request.getAge());
        profile.setHeight(request.getHeight());
        profile.setWeight(request.getWeight());
        profile.setGender(request.getGender());
        profile.setDailyStepGoal(request.getDailyStepGoal());
        profile.setDailyCalorieGoal(request.getDailyCalorieGoal());
        profile.setGoals(request.getGoals());
        profile.setUser(user);

        UserProfile saved = userProfileRepository.save(profile);

        return mapToDTO(saved);
    }

    public UserProfileResponseDTO getProfile(Long userId){
        UserProfile userProfile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No User Found.."));

        return mapToDTO(userProfile);
    }

    public UserProfileResponseDTO updateProfile(Long userId, UserProfileRequestDTO request){
        UserProfile userProfile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User Not found"));

        userProfile.setAge(request.getAge());
        userProfile.setHeight(request.getHeight());
        userProfile.setWeight(request.getWeight());
        userProfile.setGender(request.getGender());
        userProfile.setDailyStepGoal(request.getDailyStepGoal());
        userProfile.setDailyCalorieGoal(request.getDailyCalorieGoal());
        userProfile.setGoals(request.getGoals());

        UserProfile updated = userProfileRepository.save(userProfile);

        return mapToDTO(updated);
    }

    private UserProfileResponseDTO mapToDTO(UserProfile profile){

        return UserProfileResponseDTO.builder()
                .id(profile.getId())
                .age(profile.getAge())
                .height(profile.getHeight())
                .weight(profile.getWeight())
                .gender(profile.getGender())
                .dailyStepGoal(profile.getDailyStepGoal())
                .dailyCalorieGoal(profile.getDailyCalorieGoal())
                .goals(profile.getGoals())
                .userId(profile.getUser().getId())
                .build();
    }}
