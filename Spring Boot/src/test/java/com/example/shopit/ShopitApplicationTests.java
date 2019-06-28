package com.example.shopit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ShopitApplicationTests {

    /*public CartRepository repository;

    public CartService serviceUnderTest;

    @Before
    public void setup() {
        repository = Mockito.mock(CartRepository.class);

        serviceUnderTest = new CartService(repository);
    }

    @Test
    public void shouldAddItemToCart() {
        Product product = new Product();

        serviceUnderTest.addProd(product, userid);

        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    public void shouldFindCartById() {
        Cart cart = new Cart();
        cart.setProductid(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(cart));

        Cart result = serviceUnderTest.get(1L);

        Assert.assertEquals(result.getProductid(), cart.getProductid());
    }

    @Test
    public void shouldDeleteCartById() {
        Cart cart = new Cart();
        cart.setProductid(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(cart));

        serviceUnderTest.delete(1L);

        Mockito.verify(repository, Mockito.times(1)).deleteById(cart.getProductid());
    }

    @Test
    public void shouldUpdateCart()
    {
        Cart cart = new Cart();
        cart.setProductid(1L);
        cart.setQuantity(1L);
        cart.setCostofeach(1);
        cart.setCost(1);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(cart));

        CartProd cartProd = new CartProd();
        cartProd.setCartList(new ArrayList<Cart>(Collections.singletonList(cart)));
        serviceUnderTest.updateCart(cartProd);

        Assert.assertEquals((int)cart.getCost(),(int)repository.findById(1L).get().getCost());
    }*/

}
