package com.ecommerce.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.project.dto.ProductDto;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;

@Service
public interface ProductService {

  void createProduct(ProductDto productDto, Category category);

  ProductDto getProductDto(Product product);

  List<ProductDto> getProducts();

  void updateProduct(ProductDto productDto, int id) throws Exception;
}
