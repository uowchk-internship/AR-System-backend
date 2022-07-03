package com.example.arsystembackend.service;

import com.example.arsystembackend.entity.ProgramPlan;

import java.util.List;

public interface ProgramPlanServices {

    public void saveData(List<ProgramPlan> programPlanList);

    public List<ProgramPlan> getAll();

    public long count();

    public void removeAll();

}
