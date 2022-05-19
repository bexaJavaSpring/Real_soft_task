package com.example.real_soft_task.repository_service;

import com.example.real_soft_task.dto.ContactDto;
import com.example.real_soft_task.model.Contact;
import com.example.real_soft_task.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContactService implements ContactRepository {

    public final JdbcTemplate jdbcTemplate;


    @Override
    public int addContact(ContactDto dto) {
        return jdbcTemplate.update("insert into \"Contact\" (name,number,status,category_id,active) values (?,?,?,?,?)",
                dto.getName(),dto.getNumber(),dto.getStatus(),dto.getCategoryId(),dto.isActive());
    }

    @Override
    public int updateContact(ContactDto dto, Integer id) {
        return jdbcTemplate.update("update \"Contact\" set name=?,number=?,status=?,category_id=?,active=? where id=?",
                dto.getName(),dto.getNumber(),dto.getStatus(),dto.getCategoryId(),dto.isActive(),id);
    }

    @Override
    public Contact findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from \"Contact\" where id=?", BeanPropertyRowMapper.newInstance(Contact.class),id);
    }

    @Override
    public List<Contact> findAll() {
          return jdbcTemplate.query("select * from \"Contact\" where active = true;", BeanPropertyRowMapper.newInstance(Contact.class));
    }

    @Override
    public int deleteContact(Integer id) {
        return jdbcTemplate.update("update \"Category\" set active = false where id = ?",id);
    }
}
