package com.ecommerce.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.project.dto.ProductDto;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.model.User;
import com.ecommerce.project.model.Wishlist;

@Service
public interface WishlistService {

  void createWishlist(Wishlist wishlist);

  Boolean isProductAlreadyAdded(User user, Product product);

  List<ProductDto> getWishlistForUser(User user);

}
