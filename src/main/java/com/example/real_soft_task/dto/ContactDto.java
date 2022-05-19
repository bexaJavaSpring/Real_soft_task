package com.example.real_soft_task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDto {
  private String name;
  private String number;
  private String status;
  private Integer categoryId;
  private boolean active;
}
