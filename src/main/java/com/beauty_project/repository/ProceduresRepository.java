package com.beauty_project.repository;

import com.beauty_project.domain.Procedures;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class ProceduresRepository {
    private final SessionFactory sessionFactory;

    public ProceduresRepository() {
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public Procedures getProcedureById (int id){
        Session session = sessionFactory.openSession();
        Procedures procedure = session.get(Procedures.class, id);
        session.close();
        if (procedure != null){
            return procedure;
        }
        return new Procedures();
    }
}
