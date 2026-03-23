package com.kalyan.CoreDesk.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemainderRequestDTO {

    private String title;

    private String description;

    private LocalDate remainderDate;

    private LocalTime remainderTime;

}
