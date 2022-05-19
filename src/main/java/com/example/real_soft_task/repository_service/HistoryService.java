package com.example.real_soft_task.repository_service;

import com.example.real_soft_task.dto.HistoryDto;
import com.example.real_soft_task.model.History;
import com.example.real_soft_task.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HistoryService implements HistoryRepository {

public final JdbcTemplate jdbcTemplate;


    @Override
    public int addHistory(HistoryDto dto) {
        return jdbcTemplate.update("insert into history(userId,createdAt,action,object,\"objectName\" values (?,?,?,?,?)",
                dto.getUserId(),dto.getCreatedAt(),dto.getObject(),dto.getObjectName());
    }



    @Override
    public List<History> findAll() {
        return jdbcTemplate.query("select *  from history", BeanPropertyRowMapper.newInstance(History.class));
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public int updateHistory(HistoryDto dto, Integer id) {
        return 0;
    }
}
