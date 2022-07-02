package com.example.arsystembackend.dao;

import com.example.arsystembackend.entity.Argo10;

import java.util.List;

public interface Argo10DAO {

    public void saveData(List<Argo10> argo10List);

    public List<Argo10> getAll();

    public void testConnection();

    public void removeAll();
}
