package com.ecommerce.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.project.dto.ProductDto;
import com.ecommerce.project.model.Category;

@Service
public interface ProductService {

  void createProduct(ProductDto productDto, Category category);

  List<ProductDto> getProducts();

  void updateProduct(ProductDto productDto, int id) throws Exception;
}
