package com.ecommerce.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.project.model.Category;

@Service
public interface CategoryService {

  List<Category> listCategory();

  boolean isCategoryPresent(int id);

  Optional<Category> findCategory(int id);

  Category createCategory(Category category);

  Category updateCategory(int id, Category category);
}
