package com.ecommerce.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerce.project.dto.ProductDto;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.repository.ProductRepo;
import com.ecommerce.project.service.ProductService;

public class ProductServiceImpl implements ProductService {
  private ProductRepo productRepo;

  @Override
  public void createProduct(ProductDto productDto, Category category) {
    Product product = new Product();

    product.setName(productDto.getName());
    product.setDescription(productDto.getDescription());
    product.setImageUrl(productDto.getImageUrl());
    product.setPrice(productDto.getPrice());
    product.setCategory(category);

    productRepo.save(product);
  }

  public ProductDto getProductDto(Product product) {
    ProductDto productDto = new ProductDto();

    productDto.setId(product.getId());
    productDto.setName(product.getName());
    productDto.setDescription(product.getDescription());
    productDto.setImageUrl(product.getImageUrl());
    productDto.setPrice(product.getPrice());
    productDto.setCategoryId(product.getCategory().getId());

    return productDto;
  }

  @Override
  public List<ProductDto> getProducts() {
    List<Product> productList = productRepo.findAll();

    List<ProductDto> productDtos = new ArrayList<>();
    for (Product product : productList)
      productDtos.add(getProductDto(product));

    return productDtos;
  }

  @Override
  public void updateProduct(ProductDto productDto, int id) throws Exception {
    Optional<Product> optionalProduct = productRepo.findById(id);

    if (!optionalProduct.isPresent())
      throw new Exception("Product does not exist");

    Product product = optionalProduct.get();

    product.setName(productDto.getName());
    product.setDescription(productDto.getDescription());
    product.setImageUrl(productDto.getImageUrl());
    product.setPrice(productDto.getPrice());

    productRepo.save(product);
  }
}
