package com.example.real_soft_task.controller;

import com.example.real_soft_task.dto.CategoryDto;
import com.example.real_soft_task.dto.ContactDto;
import com.example.real_soft_task.dto.HistoryDto;
import com.example.real_soft_task.model.Category;
import com.example.real_soft_task.repository_service.CategoryService;
import com.example.real_soft_task.repository_service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final HistoryService historyService;

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
        String message = "";
        int save = categoryService.addCategory(dto);
        if (save == 1) {
            // auditing :
            HistoryDto historyDto = new HistoryDto();
            historyDto.setUserId(historyDto.getUserId());
            historyDto.setAction("Add to category");
            historyDto.setObject("Category");
            historyDto.setObjectName(dto.getName());
            historyService.addHistory(historyDto);

            message = "success";
        } else {
            message = "failed";
        }
        model.addAttribute("message", message);
        List<Category> all = categoryService.findAll();
        model.addAttribute("categoryList", all);
        return "category";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        model.addAttribute("categoryList", categoryService.findAll());
        return "category_edit";
    }

    @PostMapping("/edit/{id}")
    public String saveEditCategory(@ModelAttribute CategoryDto dto, @PathVariable Integer id, Model model) {
        String message = "";
        int save = categoryService.updateCategory(dto, id);
        if (save == 1) {
            HistoryDto historyDto = new HistoryDto();
            historyDto.setUserId(historyDto.getUserId());
            historyDto.setAction("editing category");
            historyDto.setObject("Category");
            historyDto.setObjectName(dto.getName());
            historyService.updateHistory(historyDto, id);
            message = "success";
        } else {
            message = "fail";
        }

        model.addAttribute("message", message);
        model.addAttribute("categoryList", categoryService.findAll());
        return "category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id, Model model) {
        String message = "";
        int save = categoryService.deleteCategory(id);
        if (save == 1) {
            Category category = categoryService.findById(id);

            HistoryDto dto = new HistoryDto();
            dto.setUserId(dto.getUserId());
            dto.setAction("delete category");
            dto.setObject("Category");
            dto.setObjectName(category.getName());
            historyService.delete(id);

            message = "success";

        } else {
            message = "failed";
        }
        model.addAttribute("message", message);
        model.addAttribute("categoryList", categoryService.findAll());
        return "category";
    }

    @GetMapping("/show")
    public String categoryList(Model model) {
        List<Category> all = categoryService.findAll();
        model.addAttribute("message", "All category");
        model.addAttribute("categoryList", all);
        return "category-show";
    }

}





