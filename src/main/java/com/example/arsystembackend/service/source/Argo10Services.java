package com.example.arsystembackend.service.source;

import com.example.arsystembackend.entity.Argo10;

import java.util.List;

public interface Argo10Services {

    public void saveData(List<Argo10> argo10List);

    public List<Argo10> getAll();

    public long count();

    public void removeAll();

}