package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.DTO.Request.ActivityRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.ActivityResponseDTO;
import com.kalyan.CoreDesk.Service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/{userId}")
    public ActivityResponseDTO updateActivity(@PathVariable Long userId, @RequestBody ActivityRequestDTO request){
        return activityService.updateSteps(userId, request);
    }

}
