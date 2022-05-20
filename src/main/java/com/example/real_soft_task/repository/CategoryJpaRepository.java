//package com.example.real_soft_task.repository;
//
//import com.example.real_soft_task.model.Categoriya;
//import com.example.real_soft_task.model.Category;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//
//
//public interface CategoryJpaRepository extends JpaRepository<Categoriya,Integer> {
//   @Query(value = "select s from Categoriya s where s.name like %?1%",nativeQuery = true)
//    Page<Category> findByName(String name, Pageable pageable);
//}
