package com.example.arsystembackend.dao;

import com.example.arsystembackend.entity.source.Argo10;
import com.example.arsystembackend.entity.source.ProgramPlan;

import java.util.List;

public interface ProgramPlanDAO {

    public void saveData(List<ProgramPlan> programPlanList);

    public List<ProgramPlan> getAll();

    public List<ProgramPlan> getSingleProgramPlan(String programName);

    public long count();

    public void removeAll();

}
