package com.beauty_project.repository;

import com.beauty_project.domain.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {
    private final SessionFactory sessionFactory;

    public EmployeeRepository() {
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();
        session.close();
        if (employee != null) {
            return employee;
        }
        return new Employee();
    }
}
