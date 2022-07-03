package com.example.arsystembackend.service;

import com.example.arsystembackend.entity.Argo10;
import com.example.arsystembackend.entity.Argo11;

import java.util.List;

public interface Argo11Services {

    public void saveData(List<Argo11> argo11List);

    public List<Argo11> getAll();

    public void testConnection();

    public void removeAll();

}
