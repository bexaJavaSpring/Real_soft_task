package com.example.real_soft_task.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RegisterDto {
    private String email;
    private String userName;
    private String password;
    private String confirmPassword;
}
