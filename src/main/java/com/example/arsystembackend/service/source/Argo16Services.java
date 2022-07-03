package com.example.arsystembackend.service.source;

import com.example.arsystembackend.entity.Argo16;

import java.util.List;

public interface Argo16Services {

    public void saveData(List<Argo16> argo16List);

    public List<Argo16> getAll();

    public long count();

    public void removeAll();

}