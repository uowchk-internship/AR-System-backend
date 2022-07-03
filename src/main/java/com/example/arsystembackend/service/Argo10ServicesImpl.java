package com.example.arsystembackend.service;

import com.example.arsystembackend.dao.Argo10DAOService;
import com.example.arsystembackend.entity.Argo10;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class Argo10ServicesImpl implements Argo10Services{
    private Argo10DAOService argo10DAOService;

    @Autowired
    public Argo10ServicesImpl(Argo10DAOService argo10DAOService) {
        this.argo10DAOService = argo10DAOService;
    }

    @Override
    @Transactional
    public void saveData(List<Argo10> argo10List) {
        argo10DAOService.saveData(argo10List);
    }

    @Override
    @Transactional
    public List<Argo10> getAll() {
        return argo10DAOService.getAll();
    }

    @Override
    @Transactional
    public long count() {
        return argo10DAOService.count();
    }

    @Override
    @Transactional
    public void removeAll() {
        argo10DAOService.removeAll();
    }
}
