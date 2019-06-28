package com.example.shopit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    List<Product> listAll(){
        return repo.findAll();
    }

    public void save(Product product) {
        repo.save(product);
    }

    public Product get(long id){
        if (repo.findById(id).isPresent())
            return repo.findById(id).get();
        return null;
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
