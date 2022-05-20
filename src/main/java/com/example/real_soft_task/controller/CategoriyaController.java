//package com.example.real_soft_task.controller;
//
//import com.example.real_soft_task.model.Categoriya;
//import com.example.real_soft_task.repository.CategoryJpaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//@RequestMapping("/categoriya")
//@RestController
//public class CategoriyaController {
//    @Autowired
//    CategoryJpaRepository categoryJpaRepository;
//
//    @GetMapping("/page")
//    public Page<Categoriya> findAll(@RequestParam Optional<String> name, @RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy){
//        return categoryJpaRepository.findByName(name.orElse(" "),new PageRequest(page.orElse(0),5), Sort.Direction.ASC,sortBy.orElse("id"));
//    }}

