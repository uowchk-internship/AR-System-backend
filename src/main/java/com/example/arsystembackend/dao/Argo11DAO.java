package com.example.arsystembackend.dao;

import com.example.arsystembackend.entity.Argo11;

import java.util.List;

public interface Argo11DAO {
    public void saveData(List<Argo11> argo11List);

    public List<Argo11> getAll();

    public long count();

    public void removeAll();

}
