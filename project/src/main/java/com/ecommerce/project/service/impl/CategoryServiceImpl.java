package com.ecommerce.project.service.impl;

import java.util.List;
import java.util.Optional;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepo;
import com.ecommerce.project.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
  private CategoryRepo categoryRepo;

  @Override
  public Category createCategory(Category category) {
    return categoryRepo.save(category);
  }

  @Override
  public boolean isCategoryPresent(int id) {
    return categoryRepo.findById(id).isPresent();
  }

  @Override
  public Optional<Category> findCategory(int id) {
    return categoryRepo.findById(id);
  }

  @Override
  public List<Category> listCategory() {
    return categoryRepo.findAll();
  }

  @Override
  public Category updateCategory(int id, Category updatedCategory) {
    Category category = categoryRepo.getReferenceById(id);

    category.setCategoryName(updatedCategory.getCategoryName());
    category.setDescription(updatedCategory.getDescription());
    category.setImageUrl(updatedCategory.getImageUrl());

    return categoryRepo.save(category);
  }
}
