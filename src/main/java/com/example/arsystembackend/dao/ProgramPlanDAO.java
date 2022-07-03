package com.example.arsystembackend.dao;

import com.example.arsystembackend.entity.Argo29;
import com.example.arsystembackend.entity.ProgramPlan;

import java.util.List;

public interface ProgramPlanDAO {

    public void saveData(List<ProgramPlan> programPlanList);

    public List<ProgramPlan> getAll();

    public long count();

    public void removeAll();

}
