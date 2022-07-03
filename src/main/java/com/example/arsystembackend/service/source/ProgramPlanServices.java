package com.example.arsystembackend.service.source;

import com.example.arsystembackend.entity.source.ProgramPlan;

import java.util.List;

public interface ProgramPlanServices {

    public void saveData(List<ProgramPlan> programPlanList);

    public List<ProgramPlan> getAll();

    public List<ProgramPlan> getSingleProgramPlan(String programName);

    public long count();

    public void removeAll();

}
