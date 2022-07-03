package com.example.arsystembackend.dao;

import com.example.arsystembackend.entity.Argo16;

import java.util.List;

public interface Argo16DAO {
    public void saveData(List<Argo16> argo16List);

    public List<Argo16> getAll();

    public void testConnection();

    public void removeAll();

}
