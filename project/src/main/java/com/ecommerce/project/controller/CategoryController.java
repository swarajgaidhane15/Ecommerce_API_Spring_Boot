package com.ecommerce.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.common.ApiResponse;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  CategoryService categoryService;

  @GetMapping("/")
  public List<Category> listCategory() {
    return categoryService.listCategory();
  }

  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
    Category newCategory = categoryService.createCategory(category);

    return new ResponseEntity<>(
        new ApiResponse(true, "Created new Category", newCategory),
        HttpStatus.CREATED);
  }

  @PostMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
    if (!categoryService.isCategoryPresent(id))
      return new ResponseEntity<>(
          new ApiResponse(false, "Category does not exist", null),
          HttpStatus.NOT_FOUND);

    Category updatedCategory = categoryService.updateCategory(id, category);

    return new ResponseEntity<>(
        new ApiResponse(true, "Updated the Category", updatedCategory),
        HttpStatus.OK);
  }
}
