package com.samar.productspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class ProductspringApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(ProductspringApplication.class, args);

		ProductService service=context.getBean(ProductService.class);

		List<Product> products=service.getAllProduct();
		for (Product p:products){
			System.out.println(p);
		}
	}


}
