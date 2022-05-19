package com.example.real_soft_task.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDto {
    private String userName;
    private String email;
    private String password;
}
