package com.example.shopit.service;

import com.example.shopit.model.CartProd;
import com.example.shopit.dao.CartRepository;
import com.example.shopit.model.Cart;
import com.example.shopit.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository repo;

    public CartService(CartRepository repo) {
        this.repo = repo;
    }

    public List<Cart> listAll(){
        return repo.findAll();
    }

    public void save(Cart cart) {
            repo.save(cart);
    }

    public String addProd(Product product, Long userid)
    {
        Cart cart;
        if (repo.findById(userid +"-"+ product.getProductid()).isPresent())
        {
            cart = get(userid +"-"+ product.getProductid());
            if (cart.getQuantity()==5)
                return "{\"result\":\"Out Of Stock\"}";
            long val = cart.getQuantity()+1;
            cart.setCost((cart.getCost()/cart.getQuantity())*val);
            cart.setQuantity(val);
            cart.setCostofeach(cart.getCost()/cart.getQuantity());
        }
        else
        {
            cart = new Cart();
            cart.setCartid(userid +"-"+ product.getProductid());
            cart.setName(product.getProductname());
            cart.setDescription(product.getProductdescription());
            cart.setCost(product.getProductvalue());
            cart.setCostofeach(product.getProductvalue());
            cart.setQuantity(1);
        }
        save(cart);
        return "{\"result\":\"true\"}";
    }

    public Cart get(String id){
        if (repo.findById(id).isPresent())
            return repo.findById(id).get();
        return null;
    }

    public void delete(String id){
        if (get(id)==null)
            return;
        repo.deleteById(id);
    }

    public float getTotal(Long userid)
    {
        List<Cart> cart = listAll();
        double total = cart.stream().filter(cart1 ->
                (Integer.parseInt(cart1.getCartid().split("-")[0])==userid)
        ).mapToDouble(Cart::getCost).sum();
        return (float)total;
    }

    public void clearCart() {
        repo.deleteAll();
    }

    public void updateCart(CartProd listCart) {
        listCart.getCartList().forEach(cart->{
            cart.setCost(cart.getCostofeach()*cart.getQuantity());
            save(cart);
        });
    }

    public List<Cart> listAll(Long id) {
        List<Cart> userCart = new ArrayList<>();
        List<Cart> cartList = repo.findAll();
        cartList.forEach(cart -> {
            String userid = cart.getCartid().split("-")[0];
            if (Integer.parseInt(userid)==id)
                userCart.add(cart);
        });
        return userCart;
    }
}
