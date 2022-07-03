package com.example.arsystembackend.service;

import com.example.arsystembackend.dao.Argo11DAOService;
import com.example.arsystembackend.entity.Argo11;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class Argo11ServicesImpl implements Argo11Services{
    private Argo11DAOService argo11DAOService;

    @Autowired
    public Argo11ServicesImpl(Argo11DAOService argo11DAOService) {
        this.argo11DAOService = argo11DAOService;
    }

    @Override
    @Transactional
    public void saveData(List<Argo11> argo11List) {
        argo11DAOService.saveData(argo11List);
    }

    @Override
    @Transactional
    public List<Argo11> getAll() {
        return argo11DAOService.getAll();
    }

    @Override
    @Transactional
    public long count() {
        return argo11DAOService.count();
    }

    @Override
    @Transactional
    public void removeAll() {
        argo11DAOService.removeAll();
    }

}
