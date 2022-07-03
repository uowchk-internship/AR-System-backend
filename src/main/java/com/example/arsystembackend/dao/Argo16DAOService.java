package com.example.arsystembackend.dao;

import com.example.arsystembackend.entity.Argo11;
import com.example.arsystembackend.entity.Argo16;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class Argo16DAOService implements Argo16DAO{
    private EntityManager entityManager;

    @Autowired
    public Argo16DAOService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void saveData(List<Argo16> argo16List) {
        Session currentSession = entityManager.unwrap(Session.class);
        for (Argo16 argo16:argo16List){
            currentSession.saveOrUpdate(argo16);
        }
    }

    @Override
    public List<Argo16> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("FROM Argo16", Argo16.class);
        List<Argo16> users = query.getResultList();
        return users;
    }

    @Override
    public void testConnection() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("FROM Argo16", Argo16.class);
        query.getFirstResult();
    }

    @Override
    public void removeAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.createQuery("DELETE FROM Argo16")
                .executeUpdate();
    }


}
