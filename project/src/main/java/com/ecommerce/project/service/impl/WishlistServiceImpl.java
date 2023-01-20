package com.ecommerce.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.project.dto.ProductDto;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.model.User;
import com.ecommerce.project.model.Wishlist;
import com.ecommerce.project.repository.WishlistRepo;
import com.ecommerce.project.service.ProductService;
import com.ecommerce.project.service.WishlistService;

public class WishlistServiceImpl implements WishlistService {

  @Autowired
  WishlistRepo wishlistRepo;

  private ProductService productService;

  @Override
  public void createWishlist(Wishlist wishlist) {
    wishlistRepo.save(wishlist);
  }

  @Override
  public Boolean isProductAlreadyAdded(User user, Product product) {
    if (Objects.nonNull(wishlistRepo.findByUser(user)) || Objects.nonNull(wishlistRepo.findByProduct(product)))
      return true;

    return false;
  }

  @Override
  public List<ProductDto> getWishlistForUser(User user) {
    final List<Wishlist> wishlistItems = wishlistRepo.findByAllByUserOrderByCreatedDateDesc(user);

    List<ProductDto> productDtos = new ArrayList();

    for (Wishlist item : wishlistItems) {
      productDtos.add(productService.getProductDto(item.getProduct()));
    }

    return productDtos;
  }
}
