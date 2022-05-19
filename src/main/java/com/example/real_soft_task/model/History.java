package com.example.real_soft_task.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class History {
 private Integer id;
 private Integer user_id;
 private Date created_at;
 private String action;
 private String object;
 private String object_name;

 public History(Integer userId, Date createdAt, String action, String object, String object_name) {
  this.user_id = userId;
  this.created_at = createdAt;
  this.action = action;
  this.object = object;
  this.object_name = object_name;
 }
}
