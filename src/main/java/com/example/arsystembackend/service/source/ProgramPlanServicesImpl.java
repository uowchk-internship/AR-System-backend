package com.example.arsystembackend.service.source;

import com.example.arsystembackend.dao.ProgramPlanDAOService;
import com.example.arsystembackend.entity.ProgramPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProgramPlanServicesImpl implements ProgramPlanServices{

    private ProgramPlanDAOService programPlanDAOService;

    @Autowired
    public ProgramPlanServicesImpl(ProgramPlanDAOService programPlanDAOService) {
        this.programPlanDAOService = programPlanDAOService;
    }

    @Override
    @Transactional
    public void saveData(List<ProgramPlan> programPlanList) {
        programPlanDAOService.saveData(programPlanList);
    }

    @Override
    @Transactional
    public List<ProgramPlan> getAll() {
        return programPlanDAOService.getAll();
    }

    @Override
    @Transactional
    public long count() {
        return programPlanDAOService.count();
    }

    @Override
    @Transactional
    public void removeAll() {
        programPlanDAOService.removeAll();
    }
}
