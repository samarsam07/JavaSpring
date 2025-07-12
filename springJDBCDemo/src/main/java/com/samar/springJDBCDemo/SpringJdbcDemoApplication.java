package com.samar.springJDBCDemo;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.samar.springJDBCDemo.model.Alein;
import com.samar.springJDBCDemo.repo.AleinRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLOutput;

@SpringBootApplication
public class SpringJdbcDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(SpringJdbcDemoApplication.class, args);
		Alein alein1=context.getBean(Alein.class);
		alein1.setId(104);
		alein1.setName("bong");
		alein1.setTech("java");

		AleinRepo repo=context.getBean(AleinRepo.class);
		repo.save(alein1);
		System.out.println(repo.findAll());


	}

}
