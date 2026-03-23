package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.DTO.Request.RemainderRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.RemainderResponseDTO;
import com.kalyan.CoreDesk.Service.RemainderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remainder/{userId}")
@RequiredArgsConstructor
public class RemainderController {

    private final RemainderService remainderService;

    @PostMapping("/create")
    public RemainderResponseDTO createRemainder(@PathVariable Long userId, @RequestBody RemainderRequestDTO request){
        return remainderService.createRemainder(userId, request);
    }

    @GetMapping("/getRemainders")
    public List<RemainderResponseDTO> getUserReminders(@PathVariable Long userId){
        return remainderService.getUserReminders(userId);
    }

    @DeleteMapping("/delete/{remainderId}")
    public String deleteReminder(@PathVariable("userId") Long userId, @PathVariable("remainderId") Long reminderId){
        return remainderService.deleteReminder(reminderId);
    }

    @PutMapping("/update/{remainderId}")
    public RemainderResponseDTO updateReminder(@PathVariable("userId") Long userId, @PathVariable("remainderId") Long reminderId, @RequestBody RemainderRequestDTO request){
        return remainderService.updateReminder(reminderId, request);
    }
}