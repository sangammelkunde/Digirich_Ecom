package com.wipro.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.wipro.productservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findById(int id);

    Optional<Category> findByCategoryName(String name);

}
