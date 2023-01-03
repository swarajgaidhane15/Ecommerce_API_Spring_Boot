package com.ecommerce.project.controller;

import java.util.List;
import java.util.Optional;

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
import com.ecommerce.project.dto.ProductDto;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import com.ecommerce.project.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  ProductService productService;

  @Autowired
  CategoryService categoryService;

  @GetMapping("/")
  public ResponseEntity<List<ProductDto>> getProducts() {
    List<ProductDto> products = productService.getProducts();

    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
    if (!categoryService.isCategoryPresent(productDto.getCategoryId()))
      return new ResponseEntity<>(
          new ApiResponse(false, "Category does not exist", null),
          HttpStatus.BAD_REQUEST);

    Optional<Category> category = categoryService.findCategory(productDto.getCategoryId());
    productService.createProduct(productDto, category.get());

    return new ResponseEntity<>(
        new ApiResponse(true, "Product created sucessfully", productDto),
        HttpStatus.CREATED);
  }

  @PostMapping("/update/{id}")
  public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") int id, @RequestBody ProductDto productDto)
      throws Exception {
    if (!categoryService.isCategoryPresent(productDto.getCategoryId()))
      return new ResponseEntity<>(
          new ApiResponse(false, "Category does not exist", null),
          HttpStatus.BAD_REQUEST);

    productService.updateProduct(productDto, id);

    return new ResponseEntity<>(
        new ApiResponse(true, "Product updated sucessfully", productDto),
        HttpStatus.CREATED);
  }
}
