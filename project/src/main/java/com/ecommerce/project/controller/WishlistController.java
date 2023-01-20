package com.ecommerce.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.common.ApiResponse;
import com.ecommerce.project.dto.ProductDto;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.model.User;
import com.ecommerce.project.model.Wishlist;
import com.ecommerce.project.service.AuthenticationTokenService;
import com.ecommerce.project.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
  private WishlistService wishlistService;

  private AuthenticationTokenService authService;

  @GetMapping("/{token}")
  public ResponseEntity<List<ProductDto>> getWishlist(@PathVariable("token") String token) {
    authService.authenticateToken(token);

    User user = authService.getUser(token);

    List<ProductDto> wishlistForUser = wishlistService.getWishlistForUser(user);

    return new ResponseEntity<>(wishlistForUser, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addToWishlist(@RequestBody Product product, @RequestParam("token") String token) {
    authService.authenticateToken(token);

    User user = authService.getUser(token);

    Boolean isProductAlreadyAdded = wishlistService.isProductAlreadyAdded(user, product);

    if (isProductAlreadyAdded == true)
      return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Product already in wishlist", null),
          HttpStatus.ALREADY_REPORTED);

    Wishlist wishlist = new Wishlist(user, product);

    wishlistService.createWishlist(wishlist);

    return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product added to wishlist", wishlist),
        HttpStatus.CREATED);
  }
}
