package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.DTO.Request.HabitRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.HabitResponseDTO;
import com.kalyan.CoreDesk.Service.HabitService;
import com.kalyan.CoreDesk.Utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habit/{userId}")
@RequiredArgsConstructor
public class HabitController {
    private final HabitService habitService;

    @GetMapping("/list")
    public List<HabitResponseDTO> getHabits(@PathVariable Long userId, HttpSession session) {
        SessionUtil.verifyOwnership(userId, session);
        return habitService.getHabitsByUserId(userId);
    }

    @PostMapping("/create")
    public HabitResponseDTO createHabit(@PathVariable Long userId, @RequestBody HabitRequestDTO request, HttpSession session) {
        SessionUtil.verifyOwnership(userId, session);
        return habitService.createHabit(userId, request);
    }

    @PatchMapping("/toggle/{habitId}")
    public HabitResponseDTO toggleHabit(@PathVariable Long userId, @PathVariable Long habitId, HttpSession session) {
        SessionUtil.verifyOwnership(userId, session);
        return habitService.toggleHabit(habitId);
    }
}
