package com.example.shopit.controller;

import com.example.shopit.model.CartProd;
import com.example.shopit.service.CartService;
import com.example.shopit.service.ProductService;
import com.example.shopit.service.UserService;
import com.example.shopit.model.Cart;
import com.example.shopit.model.Login;
import com.example.shopit.model.Product;
import com.example.shopit.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AppController {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    public AppController(UserService userService, ProductService productService, CartService cartService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
    }

   /* @RequestMapping(value = "/")
    public List<Product> viewUserPage(Model model){
        *//*List<User> listUser = userService.listAll();
        model.addAttribute("listUser",listUser);*//*
        return viewHomePage(model);
    }*/

    @RequestMapping(value = "/home")
    public List<Product> viewHomePage(Model model){
        List<Product> listProduct = productService.listAll();
        List<Cart> listCart = cartService.listAll();
        model.addAttribute("listProduct",listProduct);
        model.addAttribute("cartCount",listCart.size());
        return listProduct;
    }

    @RequestMapping(value = "/cart/{id}")
    public List<Cart> viewCartPage(@PathVariable(value = "id")String userid, Model model){
        CartProd cartProd = new CartProd();
        cartProd.setCartList(cartService.listAll(Long.parseLong(userid)));
        model.addAttribute("listCart",cartProd);
        return cartProd.getCartList();
    }

    @RequestMapping(value = "/cartSize/{id}")
    public int viewCartSize(@PathVariable(value = "id") String userid, Model model){
        return cartService.listAll(Long.parseLong(userid)).size();
    }

   /* @PostMapping(value = "/cart")
    public List<Cart> viewCartPage(@ModelAttribute CartProd listCart,Model model)
    {
        cartService.updateCart(listCart);
        CartProd cartProd = new CartProd();
        cartProd.setCartList(cartService.listAll());
        model.addAttribute("listCart",cartProd);
        return cartProd.getCartList();
    }*/

    @PostMapping(value = "/cart")
    public List<Cart> viewCartPage(@RequestBody List<Cart> listCart,Model model)
    {
        CartProd cartProd = new CartProd();
        cartProd.setCartList(listCart);
        cartService.updateCart(cartProd);
        cartProd.setCartList(cartService.listAll());
        model.addAttribute("listCart",cartProd);
        return cartProd.getCartList();
    }

    @RequestMapping(value = "/cartDel/{cartid}")
    public List<Cart> deleteCartitem(@PathVariable(name="cartid") String cartid,Model model)
    {
        cartService.delete(cartid);
        CartProd cartProd = new CartProd();
        cartProd.setCartList(cartService.listAll());
        model.addAttribute("listCart",cartProd);
        return cartProd.getCartList();
    }

    @RequestMapping(value = "/buyProd/{id}/{id2}")
    public String viewBuyPage(Model model, @PathVariable(name = "id") Long productid,@PathVariable(value = "id2") String userid){
        Product product = productService.get(productid);
        if (product==null)
            return "{\"result\":\"false\"}";
        return cartService.addProd(product,Long.parseLong(userid));
        /*List<Product> listProduct = productService.listAll();
        List<Cart> listCart = cartService.listAll();
        model.addAttribute("listProduct",listProduct);
        model.addAttribute("cartCount",listCart.size());*/
        //return "true";
    }

    /*@RequestMapping(value = "/buy")
    public List viewBuyPage(Model model){
        List<Cart> listCart = cartService.listAll();
        if(listCart.size()==0)
           return viewHomePage(model);
        model.addAttribute("listCart",listCart);
        model.addAttribute("total",cartService.getTotal(userid));
        return listCart;
    }*/

    @RequestMapping(value = "/payment/{id}")
    public float viewPayPage(@PathVariable(value = "id") String userid, Model model){
        /*model.addAttribute("total",cartService.getTotal());
        if (cartService.getTotal()==0)
           return viewHomePage(model);*/
        float total =cartService.getTotal(Long.parseLong(userid));
        cartService.clearCart();
        return total;
    }

    @RequestMapping(value = "/getTotal/{id}")
    public float viewTotal(@PathVariable(value = "id") String userid, Model model){
        float total =cartService.getTotal(Long.parseLong(userid));
        return total;
    }

    @PostMapping(value = "/checkLogin")
    public boolean checkLogin(@RequestBody Login login, Model model)
    {
        return userService.validate(login);
    }

    @RequestMapping(value = "/emailcheck/{email}")
    public boolean emailCheck(@PathVariable(value = "email") String username,Model model)
    {
        return userService.checkUsername(username);
    }

    @RequestMapping(value = "/phonecheck/{phone}")
    public boolean phoneCheck(@PathVariable(value = "phone") String phone,Model model)
    {
        return userService.checkPhone(phone);
    }

    @PostMapping(value = "/register")
    public void registerUser(@RequestBody User user, Model model)
    {
        userService.save(user);
    }

    @PostMapping(value = "/loginas")
    public User LoginAs(@RequestBody Login login,Model model)
    {
        return userService.loginAs(login);
    }



}
