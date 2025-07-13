package com.samar.productspringweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService service;
    @GetMapping("products")
    public List<Product> getAllProduct(){
        return service.getAllProduct();
    }
    @GetMapping("/product/{name}")
    @ResponseBody
    public Product getProduct(@PathVariable("name") String name){
        return service.getProduct(name);
    }
    @PostMapping("/product")
    public void addProduct(@RequestBody Product product){
        service.addProduct(product);
    }
}
