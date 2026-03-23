package com.kalyan.CoreDesk.Service;

import com.kalyan.CoreDesk.DTO.Request.ActivityRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.ActivityResponseDTO;
import com.kalyan.CoreDesk.Model.Activity;
import com.kalyan.CoreDesk.Model.User;
import com.kalyan.CoreDesk.Repository.ActivityRepository;
import com.kalyan.CoreDesk.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityResponseDTO updateSteps(Long userId, ActivityRequestDTO request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found.."));

        double distance = (request.getSteps() * 0.762)/1000;
        double calories = request.getSteps() * 0.04;

        Activity activity = activityRepository
                .findByUserIdAndDate(userId, LocalDate.now())
                .orElse(new Activity());

        activity.setSteps(request.getSteps());
        activity.setDate(LocalDate.now());
        activity.setDistanceKm(distance);
        activity.setCalories(calories);
        activity.setUser(user);

        Activity saved = activityRepository.save(activity);

        return mapToDTO(saved);

    }

    public java.util.List<ActivityResponseDTO> getActivities(Long userId){
        return activityRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private ActivityResponseDTO mapToDTO(Activity activity){

        return ActivityResponseDTO.builder()
                .id(activity.getId())
                .steps(activity.getSteps())
                .distanceKm(activity.getDistanceKm())
                .calories(activity.getCalories())
                .date(activity.getDate())
                .userId(activity.getUser().getId())
                .build();
    }
}
