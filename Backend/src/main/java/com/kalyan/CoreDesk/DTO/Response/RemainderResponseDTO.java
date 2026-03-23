package com.kalyan.CoreDesk.DTO.Response;


import com.kalyan.CoreDesk.Model.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemainderResponseDTO {

    private Long id;

    private String title;

    private String description;

    private LocalDate remainderDate;

    private LocalTime remainderTime;

    private boolean isCompleted;

    private Long userId;
}
