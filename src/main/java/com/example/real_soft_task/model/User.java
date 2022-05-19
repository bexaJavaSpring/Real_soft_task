package com.example.real_soft_task.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String role="USER";
    private boolean active=true;

    public User( String userName, String email, String password, String role, boolean active) {
        this.username = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }
}
