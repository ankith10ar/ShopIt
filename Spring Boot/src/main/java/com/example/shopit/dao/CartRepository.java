package com.example.shopit.dao;

import com.example.shopit.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,String> {
}
