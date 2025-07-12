package com.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Student student=new Student();
        student.setTech("cpp");
        student.setId(102);
        student.setName("Sam");

        Configuration config=new Configuration();
        config.addAnnotatedClass(com.demo.Student.class);
        config.configure("hibernate.cfg.xml");
        SessionFactory factory=config.buildSessionFactory();
        Session session=factory.openSession();
//        savind data in database
//        Transaction transaction=session.beginTransaction();
//        session.persist(student);
//        transaction.commit();
//        fetching data

//        Student st=session.get(Student.class,102);
//        Student st=session.byId(Student.class).load(102);
        Student st=session.byId(Student.class).getReference(102);
        System.out.println(st);

        session.close();
        factory.close();

    }
}
