package com.example.real_soft_task.repository_service;

import com.example.real_soft_task.dto.UserDto;
import com.example.real_soft_task.model.User;
import com.example.real_soft_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserService implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public int addUser(UserDto dto) {
        return jdbcTemplate.update("insert into users (username,email,password,role,active) values (?,?,?,?,?)",
                dto.getUserName(), dto.getEmail(), dto.getPassword(), "user", true);
    }

    @Override
    public int updateUser(UserDto dto, Integer id) {
        return jdbcTemplate.update("update users set username = ? ,email =? ,password = ?  where id = ?",
                dto.getUserName(), dto.getEmail(), dto.getPassword(), id);
    }

    @Override
    public User findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from users where id = ?", User.class, id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public int deleteUser(Integer id) {
        return jdbcTemplate.update("update users set active = false where id = ?", id);
    }
}
