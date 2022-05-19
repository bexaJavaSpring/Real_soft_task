package com.example.real_soft_task.repository_service;

import com.example.real_soft_task.dto.CategoryDto;
import com.example.real_soft_task.model.Category;
import com.example.real_soft_task.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryService implements CategoryRepository {
    private final JdbcTemplate jdbcTemplate;


    @Override
    public int addCategory(CategoryDto dto) {
       return jdbcTemplate.update("INSERT into \"Category\"(name,active) values (?,?)",dto.getName(),dto.isActive());
    }

    @Override
    public int updateCategory(CategoryDto dto, Integer id) {
        return jdbcTemplate.update("UPDATE \"Category\" set name =?,active=? WHERE id=?",dto.getName(),dto.isActive(),id);
    }

    @Override
    public Category findById(Integer id) {
        Category category=jdbcTemplate.queryForObject("SELECT * FROM\"Category\" where id=?", BeanPropertyRowMapper.newInstance(Category.class),id);
        return category;
    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("Select * from \"Category\" where active=true",BeanPropertyRowMapper.newInstance(Category.class));
    }

    @Override
    public int deleteCategory(Integer id) {
        return jdbcTemplate.update("Update \"Category\" set active=false where id=?",id);
    }
}
