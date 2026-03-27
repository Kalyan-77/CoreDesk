package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.DTO.Request.ActivityRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.ActivityResponseDTO;
import com.kalyan.CoreDesk.Service.ActivityService;
import com.kalyan.CoreDesk.Utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;


    @PostMapping("/{userId}")
    public ActivityResponseDTO updateActivity(@PathVariable Long userId, @RequestBody ActivityRequestDTO request, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return activityService.updateSteps(userId, request);
    }

    @GetMapping("/{userId}")
    public List<ActivityResponseDTO> getActivities(@PathVariable Long userId,HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return activityService.getActivities(userId);
    }
}
