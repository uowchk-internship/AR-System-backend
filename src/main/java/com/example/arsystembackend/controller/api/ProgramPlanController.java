package com.example.arsystembackend.controller.api;

import com.example.arsystembackend.dao.ProgramPlanDAO;
import com.example.arsystembackend.entity.Argo10;
import com.example.arsystembackend.entity.ProgramPlan;
import com.example.arsystembackend.service.Argo10Services;
import com.example.arsystembackend.service.ProgramPlanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programPlan")
public class ProgramPlanController {

    private ProgramPlanServices programPlanServices;

    @Autowired
    public ProgramPlanController(ProgramPlanServices programPlanServices) {
        this.programPlanServices = programPlanServices;
    }

    @PostMapping("/")
    public String saveNewProgramPlan(@RequestBody List<ProgramPlan> programPlanList) {
        programPlanServices.saveData(programPlanList);
        return "done";
    }

    @GetMapping("/")
    public List<ProgramPlan> getAllProgramPlan() {
        return programPlanServices.getAll();
    }

    @GetMapping("/count")
    public long getProgramPlanCount() {
        return programPlanServices.count();
    }


    @GetMapping("/removeAll")
    public String removeAllProgramPlan() {
        programPlanServices.removeAll();
        return "done";
    }
}

