package com.example.real_soft_task.repository;

import com.example.real_soft_task.dto.HistoryDto;
import com.example.real_soft_task.model.History;

import java.util.List;

public interface HistoryRepository {

    int addHistory(HistoryDto dto);

    int updateHistory(HistoryDto dto,Integer id);

    List<History> findAll();

    int delete(Integer id);
}
