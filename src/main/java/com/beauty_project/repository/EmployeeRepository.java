package com.beauty_project.repository;

import com.beauty_project.domain.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;

@Repository
public class EmployeeRepository {
    private final SessionFactory sessionFactory;

    public EmployeeRepository() {
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.openSession();
        Employee employee = session.get(Employee.class, id);
        session.close();
        if (employee != null) {
            return employee;
        }
        return new Employee();
    }

    public ArrayList<Employee> getAllEmployees(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Employee");
        ArrayList<Employee> list = (ArrayList<Employee>) query.getResultList();
        session.close();
        return list;
    }
}
