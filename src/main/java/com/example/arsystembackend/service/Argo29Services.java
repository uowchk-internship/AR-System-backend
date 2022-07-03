package com.example.arsystembackend.service;

import com.example.arsystembackend.entity.Argo16;
import com.example.arsystembackend.entity.Argo29;

import java.util.List;

public interface Argo29Services {
    public void saveData(List<Argo29> argo29List);

    public List<Argo29> getAll();

    public long count();

    public void removeAll();

}
