package com.kalyan.CoreDesk.Service;

import com.kalyan.CoreDesk.DTO.Request.RemainderRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.RemainderResponseDTO;
import com.kalyan.CoreDesk.Model.Remainder;
import com.kalyan.CoreDesk.Model.User;
import com.kalyan.CoreDesk.Repository.RemainderRepository;
import com.kalyan.CoreDesk.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RemainderService {

    private final RemainderRepository remainderRepository;
    private final UserRepository userRepository;

    public RemainderResponseDTO createRemainder(Long userId, RemainderRequestDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not found"));

        Remainder remainder = new Remainder();

        remainder.setTitle(request.getTitle());
        remainder.setDescription(request.getDescription());
        remainder.setRemainderDate(request.getRemainderDate());
        remainder.setRemainderTime(request.getRemainderTime());
        remainder.setCompleted(false);
        remainder.setUser(user);

        Remainder saved = remainderRepository.save(remainder);

        return mapToDTO(saved);
    }

    public List<RemainderResponseDTO> getUserReminders(Long userId){

        return remainderRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public String deleteReminder(Long reminderId){

        if(!remainderRepository.existsById(reminderId)){
            throw new RuntimeException("Reminder not found");
        }

        remainderRepository.deleteById(reminderId);

        return "Reminder deleted successfully";
    }

    public RemainderResponseDTO updateReminder(Long reminderId, RemainderRequestDTO request){

        Remainder reminder = remainderRepository.findById(reminderId)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        reminder.setTitle(request.getTitle());
        reminder.setDescription(request.getDescription());
        reminder.setRemainderDate(request.getRemainderDate());
        reminder.setRemainderTime(request.getRemainderTime());

        Remainder updated = remainderRepository.save(reminder);

        return mapToDTO(updated);
    }


    private RemainderResponseDTO mapToDTO(Remainder remainder) {

        return RemainderResponseDTO.builder()
                .id(remainder.getId())
                .title(remainder.getTitle())
                .description(remainder.getDescription())
                .remainderDate(remainder.getRemainderDate())
                .remainderTime(remainder.getRemainderTime())
                .isCompleted(remainder.isCompleted())
                .userId(remainder.getUser().getId())
                .build();
    }
}
