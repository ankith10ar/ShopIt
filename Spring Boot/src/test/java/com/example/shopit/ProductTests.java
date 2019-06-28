package com.example.shopit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductTests {

    public ProductRepository repository;

    public ProductService serviceUnderTest;

    @Before
    public void setup() {
        repository = Mockito.mock(ProductRepository.class);

        serviceUnderTest = new ProductService(repository);
    }

    @Test
    public void shouldFindProductById() {
        Product product = new Product();
        product.setProductid(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));

        Product result = serviceUnderTest.get(1L);

        Assert.assertEquals(result.getProductid(), product.getProductid());
    }

    @Test
    public void shouldGetAllItems() {
        Product product = new Product();
        product.setProductid(1L);
        ArrayList<Product> products = new ArrayList<Product>(Collections.singletonList(product));
        Mockito.when(repository.findAll()).thenReturn(products);

        List<Product> items = serviceUnderTest.listAll();
        Assert.assertEquals(items.get(0).getProductid(),product.getProductid());

    }

}
