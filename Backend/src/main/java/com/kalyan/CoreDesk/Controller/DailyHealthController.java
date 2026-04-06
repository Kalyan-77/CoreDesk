package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.DTO.Request.DailyHealthRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.DailyHealthResponseDTO;
import com.kalyan.CoreDesk.Service.DailyHealthService;
import com.kalyan.CoreDesk.Utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health/{userId}")
@RequiredArgsConstructor
public class DailyHealthController {
    private final DailyHealthService dailyHealthService;

    @GetMapping("/today")
    public DailyHealthResponseDTO getHealthForToday(@PathVariable Long userId, HttpSession session) {
        SessionUtil.verifyOwnership(userId, session);
        return dailyHealthService.getHealthForToday(userId);
    }

    @PostMapping("/update")
    public DailyHealthResponseDTO updateHealth(@PathVariable Long userId, @RequestBody DailyHealthRequestDTO requestDTO, HttpSession session) {
        SessionUtil.verifyOwnership(userId, session);
        return dailyHealthService.updateHealth(userId, requestDTO);
    }
}
