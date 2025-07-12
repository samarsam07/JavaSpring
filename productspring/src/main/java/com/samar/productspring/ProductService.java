package com.samar.productspring;

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

}
