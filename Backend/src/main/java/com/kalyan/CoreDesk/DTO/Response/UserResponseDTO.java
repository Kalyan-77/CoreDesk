package com.kalyan.CoreDesk.DTO.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UserResponseDTO {
    private Long id;
    private String email;
    private String username;
}
