package com.example.arsystembackend.service.source;

import com.example.arsystembackend.entity.source.Argo11;

import java.util.List;

public interface Argo11Services {

    public void saveData(List<Argo11> argo11List);

    public List<Argo11> getAll();

    public Argo11 getSingleStudent(String sid);

    public long count();

    public void removeAll();

}
