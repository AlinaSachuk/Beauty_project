package com.beauty_project.repository;

import com.beauty_project.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;

@Repository
public class CustomerRepository {
    private final SessionFactory sessionFactory;

    public CustomerRepository() {
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public Customer getCustomerById(int id) {
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        if (customer != null) {
            return customer;
        }
        return new Customer();
    }

    public ArrayList<Customer> getAllCustomers() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Customer");
        ArrayList<Customer> list = (ArrayList<Customer>) query.getResultList();
        session.close();
        return list;
    }
}
