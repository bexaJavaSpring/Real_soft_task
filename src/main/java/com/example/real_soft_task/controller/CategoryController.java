package com.example.real_soft_task.controller;

import com.example.real_soft_task.dto.CategoryDto;
import com.example.real_soft_task.dto.ContactDto;
import com.example.real_soft_task.model.Category;
import com.example.real_soft_task.repository_service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/crud")
    public String allCategory(Model model) {
        List<Category> all = categoryService.findAll();
        model.addAttribute("message", "All Category");
        model.addAttribute("categoryList", all);
        return "category";
    }

    @GetMapping("/{id}")
    public String getOneCat(@PathVariable Integer id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("categoryList", categoryService.findAll());
      return "category";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute CategoryDto dto, Model model) {
        categoryService.addCategory(dto);
        model.addAttribute("message", "Added");
        List<Category> all = categoryService.findAll();
        model.addAttribute("categoryList", all);
        return "category";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        model.addAttribute("categoryList",categoryService.findAll());
        return "category_edit";
    }

    @PostMapping("/edit/{id}")
    public String saveEditCategory( @ModelAttribute CategoryDto dto,@PathVariable Integer id, Model model) {
        categoryService.updateCategory(dto,id);
        model.addAttribute("message", "Edited");
        model.addAttribute("categoryList", categoryService.findAll());
        return "category";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id,Model model){
       categoryService.deleteCategory(id);
       model.addAttribute("message","Deleted");
       model.addAttribute("categoryList",categoryService.findAll());
       return "category";
    }
    @GetMapping("/show")
    public String categoryList(Model model){
        List<Category> all = categoryService.findAll();
        model.addAttribute("message","All category");
        model.addAttribute("categoryList",all);
        return "category-show";
    }




}
