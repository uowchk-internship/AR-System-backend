package com.example.arsystembackend.dao;

import com.example.arsystembackend.entity.Argo10;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class Argo10DAOService implements Argo10DAO{
    private EntityManager entityManager;

    @Autowired
    public Argo10DAOService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveData(List<Argo10> argo10List) {
        Session currentSession = entityManager.unwrap(Session.class);
        for (Argo10 argo10:argo10List){
            currentSession.saveOrUpdate(argo10);
        }
    }

    @Override
    public List<Argo10> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("FROM Argo10", Argo10.class);
        List<Argo10> users = query.getResultList();
        return users;
    }

    @Override
    public void testConnection() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("FROM Argo10", Argo10.class);
        query.getFirstResult();
    }

    @Override
    public void removeAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.createQuery("DELETE FROM Argo10")
                .executeUpdate();
    }
}