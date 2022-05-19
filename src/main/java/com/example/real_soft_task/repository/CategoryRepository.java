package com.example.real_soft_task.repository;

import com.example.real_soft_task.dto.CategoryDto;
import com.example.real_soft_task.model.Category;

import java.util.List;

public interface CategoryRepository {

    int addCategory(CategoryDto category);

    int updateCategory(CategoryDto category, Integer id);

    Category findById(Integer id);

    List<Category> findAll();

    int deleteCategory(Integer id);
}
