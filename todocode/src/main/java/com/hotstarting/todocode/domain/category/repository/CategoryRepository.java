package com.hotstarting.todocode.domain.category.repository;

import com.hotstarting.todocode.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
