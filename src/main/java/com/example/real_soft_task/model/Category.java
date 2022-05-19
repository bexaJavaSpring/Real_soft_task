package com.example.real_soft_task.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Category {
    private Integer id;
    private String name;
    private boolean active;

    public Category(String name, boolean active) {
        this.name = name;
        this.active = active;
    }
}
