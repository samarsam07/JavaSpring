package com.samar.productspringweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    @Autowired
    ProductDB db;
    public List<Product> getAllProduct(){
        return db.findAll();
    }
    public Product getProduct(String name){
        return db.findByName(name);
    }
    public void addProduct(Product product){
        db.save(product);
    }
}
