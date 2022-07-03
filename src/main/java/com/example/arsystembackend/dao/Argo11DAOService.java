package com.example.arsystembackend.dao;

import com.example.arsystembackend.entity.Argo10;
import com.example.arsystembackend.entity.Argo11;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class Argo11DAOService implements Argo11DAO{
    private EntityManager entityManager;

    @Autowired
    public Argo11DAOService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveData(List<Argo11> argo11List) {
        Session currentSession = entityManager.unwrap(Session.class);
        for (Argo11 argo11:argo11List){
            currentSession.saveOrUpdate(argo11);
        }
    }

    @Override
    public List<Argo11> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("FROM Argo11", Argo11.class);
        List<Argo11> users = query.getResultList();
        return users;
    }

    @Override
    public void testConnection() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("FROM Argo11", Argo11.class);
        query.getFirstResult();
    }

    @Override
    public void removeAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.createQuery("DELETE FROM Argo11")
                .executeUpdate();
    }

}
