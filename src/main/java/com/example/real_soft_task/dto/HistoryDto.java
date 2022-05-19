package com.example.real_soft_task.dto;

import lombok.*;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class HistoryDto {
    private Integer userId;
    private Date createdAt;
    private String action;
    private String object;
    private String objectName;
}
