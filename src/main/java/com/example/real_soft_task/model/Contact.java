package com.example.real_soft_task.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Contact {
    private Integer id;
    private String name;
    private String number;
    private String status="ACTIVE";
    private Integer category_id;
    private boolean active;

    public Contact(String owner_name, String number, String status, Integer categoryId) {
        this.name = owner_name;
        this.number = number;
        this.status = status;
        this.category_id = categoryId;
    }
}
