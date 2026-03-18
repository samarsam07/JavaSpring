package com.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.demo.model.Customer;
import com.demo.model.Order;
import com.demo.model.Student;
import com.demo.model.Teacher;

public class App {
	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Student.class);
		configuration.addAnnotatedClass(Teacher.class);
		configuration.addAnnotatedClass(Customer.class);
		configuration.addAnnotatedClass(Order.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
//			Student student = new Student();
//			student.setName("samar");
//			student.setTech("java");
//
//			Teacher teacher = new Teacher();
//			teacher.setTeacherName("John");
//
//			student.setTeacher(teacher);
//			teacher.setStudent(student);
//			session.persist(teacher);
//			session.persist(student);

//			Customer customer = new Customer();
//			customer.setCustomerName("bong");
//
//			Order order1 = new Order();
//			order1.setCustomer(customer);
//			order1.setOrderName("ps5");
//
//			Order order2 = new Order();
//			order2.setCustomer(customer);
//			order2.setOrderName("intel i-7");
//
//			customer.setOrders(List.of(order1, order2));
//
//			session.persist(customer);

			String hql = "From Customer";
			Query<Customer> query = session.createQuery(hql);
			List<Customer> customers = query.list();

			for (Customer customer : customers) {
				System.out.println(customer.toString());
			}
			transaction.commit();
			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}

	}
}
