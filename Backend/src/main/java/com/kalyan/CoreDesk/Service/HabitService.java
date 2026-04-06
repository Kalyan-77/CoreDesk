package com.kalyan.CoreDesk.Service;

import com.kalyan.CoreDesk.DTO.Request.HabitRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.HabitResponseDTO;
import com.kalyan.CoreDesk.Model.Habit;
import com.kalyan.CoreDesk.Model.User;
import com.kalyan.CoreDesk.Repository.HabitRepository;
import com.kalyan.CoreDesk.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    private HabitResponseDTO mapToDTO(Habit h) {
        return HabitResponseDTO.builder()
                .id(h.getId())
                .label(h.getLabel())
                .icon(h.getIcon())
                .color(h.getColor())
                .streak(h.getStreak())
                .done(h.isDone())
                .build();
    }

    public List<HabitResponseDTO> getHabitsByUserId(Long userId) {
        return habitRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .toList();
    }

    public HabitResponseDTO createHabit(Long userId, HabitRequestDTO request) {
        User user = userRepository.findById(userId).orElseThrow();
        Habit habit = Habit.builder()
                .user(user)
                .label(request.getLabel())
                .icon(request.getIcon())
                .color(request.getColor())
                .streak(0)
                .done(false)
                .build();
        return mapToDTO(habitRepository.save(habit));
    }

    public HabitResponseDTO toggleHabit(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow();
        LocalDate today = LocalDate.now();

        if (habit.isDone()) {
            habit.setDone(false);
            if (habit.getLastCompletedDate() != null && habit.getLastCompletedDate().equals(today)) {
                habit.setStreak(Math.max(0, habit.getStreak() - 1));
                habit.setLastCompletedDate(today.minusDays(1));
            }
        } else {
            habit.setDone(true);
            if (habit.getLastCompletedDate() == null || !habit.getLastCompletedDate().equals(today)) {
                habit.setStreak(habit.getStreak() + 1);
                habit.setLastCompletedDate(today);
            }
        }
        return mapToDTO(habitRepository.save(habit));
    }
}
