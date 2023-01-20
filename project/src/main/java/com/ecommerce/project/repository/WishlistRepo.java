package com.ecommerce.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.model.User;
import com.ecommerce.project.model.Wishlist;

public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {

  boolean findByUser(User user);

  Object findByProduct(Product product);

  List<Wishlist> findByAllByUserOrderByCreatedDateDesc(User user);
}
