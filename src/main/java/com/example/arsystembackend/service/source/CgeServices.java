package com.example.arsystembackend.service.source;

import com.example.arsystembackend.entity.source.Cge;

import java.util.List;

public interface CgeServices {
    public void saveData(List<Cge> cgeList);

    public List<Cge> getAll();

    public Cge getSingleCourse(String courseCode);

    public long count();

    public void removeAll();

}
