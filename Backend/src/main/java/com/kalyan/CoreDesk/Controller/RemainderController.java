package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.DTO.Request.RemainderRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.RemainderResponseDTO;
import com.kalyan.CoreDesk.Service.RemainderService;
import com.kalyan.CoreDesk.Utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/remainder/{userId}")
@RequiredArgsConstructor
public class RemainderController {

    private final RemainderService remainderService;

    @PostMapping("/create")
    public RemainderResponseDTO createRemainder(@PathVariable Long userId, @RequestBody RemainderRequestDTO request, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return remainderService.createRemainder(userId, request);
    }

    @GetMapping("/list")
    public List<RemainderResponseDTO> getUserReminders(@PathVariable Long userId, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return remainderService.getUserReminders(userId);
    }

    @PatchMapping("/toggleComplete/{remainderId}")
    public RemainderResponseDTO toggleRemainderComplete(@PathVariable Long userId, @PathVariable Long remainderId, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        // Assuming service has or will have toggleReminderStatus
        return remainderService.toggleReminderStatus(remainderId);
    }

    @DeleteMapping("/delete/{remainderId}")
    public String deleteReminder(@PathVariable("userId") Long userId, @PathVariable("remainderId") Long reminderId, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return remainderService.deleteReminder(reminderId);
    }

    @PutMapping("/update/{remainderId}")
    public RemainderResponseDTO updateReminder(@PathVariable("userId") Long userId, @PathVariable("remainderId") Long reminderId, @RequestBody RemainderRequestDTO request, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return remainderService.updateReminder(reminderId, request);
    }
}