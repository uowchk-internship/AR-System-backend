package com.example.arsystembackend.controller.api;

import com.example.arsystembackend.entity.Argo29;
import com.example.arsystembackend.service.Argo29Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/argo29")
public class Argo29Controller {

    private Argo29Services argo29Services;

    @Autowired
    public Argo29Controller(Argo29Services argo29Services) {
        this.argo29Services = argo29Services;
    }

    @PostMapping("/")
    public String saveNewArgo29(@RequestBody List<Argo29> argo29List) {
        argo29Services.saveData(argo29List);
        return "done";
    }

    @GetMapping("/")
    public List<Argo29> getAllArgo29() {
        return argo29Services.getAll();
    }

    @GetMapping("/count")
    public int getArgo29Count() {
        return argo29Services.getAll().size();
    }


    @GetMapping("/removeAll")
    public String removeAllArgo29() {
        argo29Services.removeAll();
        return "done";
    }
}
