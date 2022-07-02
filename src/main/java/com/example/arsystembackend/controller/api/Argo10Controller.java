package com.example.arsystembackend.controller.api;

import com.example.arsystembackend.entity.Argo10;
import com.example.arsystembackend.service.Argo10Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/argo10")
public class Argo10Controller {

    private Argo10Services argo10Services;

    @Autowired
    public Argo10Controller(Argo10Services argo10Services) {
        this.argo10Services = argo10Services;
    }

    @PostMapping("/save")
    public String saveNewArgo10(@RequestBody List<Argo10> argo10List){
        argo10Services.saveData(argo10List);
        return "done";
    }

    @GetMapping("/removeAll")
    public String removeAllArgo10(){
        argo10Services.removeAll();
        return "done";
    }
}
