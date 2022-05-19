package com.example.real_soft_task.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoginDto {
    private String email;
    private String password;
}
