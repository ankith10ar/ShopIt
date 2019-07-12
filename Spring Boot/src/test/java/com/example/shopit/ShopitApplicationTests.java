package com.example.shopit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ShopitApplicationTests {

    public CartRepository repository;

    public CartService serviceUnderTest;

    @Before
    public void setup() {
        repository = Mockito.mock(CartRepository.class);

        serviceUnderTest = new CartService(repository);
    }

    @Test
    public void shouldAddItemToCart() {
        Product product = new Product();

        serviceUnderTest.addProd(product,1L);

        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    public void shouldFindCartById() {
        Cart cart = new Cart();
        cart.setCartid("1-1");
        Mockito.when(repository.findById("1-1")).thenReturn(Optional.of(cart));

        Cart result = serviceUnderTest.get("1-1");

        Assert.assertEquals(result.getCartid(), cart.getCartid());
    }

    @Test
    public void shouldDeleteCartById() {
        Cart cart = new Cart();
        cart.setCartid("1-1");
        Mockito.when(repository.findById("1-1")).thenReturn(Optional.of(cart));

        serviceUnderTest.delete("1-1");

        Mockito.verify(repository, Mockito.times(1)).deleteById(cart.getCartid());
    }

    @Test
    public void shouldUpdateCart()
    {
        Cart cart = new Cart();
        cart.setCartid("1-1");
        cart.setQuantity(1L);
        cart.setCostofeach(1);
        cart.setCost(1);
        Mockito.when(repository.findById("1-1")).thenReturn(Optional.of(cart));

        CartProd cartProd = new CartProd();
        cartProd.setCartList(new ArrayList<Cart>(Collections.singletonList(cart)));
        serviceUnderTest.updateCart(cartProd);

        Assert.assertEquals((int)cart.getCost(),(int)repository.findById("1-1").get().getCost());
    }

    @Autowired
    ApplicationContext appCtx;

    @Test
    public void whenDecryptedPasswordNeeded_GetFromService()
    {
        System.setProperty("jasypt.encryptor.password", "password");
        PropertyServiceForJasyptStarter service =
                appCtx.getBean(PropertyServiceForJasyptStarter.class);

        Assert.assertEquals("Password@1", service.getProperty());

        Environment environment = appCtx.getBean(Environment.class);

        Assert.assertEquals(
                "Password@1",
                service.getPasswordUsingEnvironment(environment));
    }
}
