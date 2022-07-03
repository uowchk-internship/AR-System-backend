package com.example.arsystembackend.controller.api;

import com.example.arsystembackend.entity.Argo16;
import com.example.arsystembackend.service.Argo16Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/argo16")
public class Argo16Controller {

    private Argo16Services argo16Services;

    @Autowired
    public Argo16Controller(Argo16Services argo16Services) {
        this.argo16Services = argo16Services;
    }

    @PostMapping("/")
    public String saveNewArgo16(@RequestBody List<Argo16> argo16List) {
        argo16Services.saveData(argo16List);
        return "done";
    }

    @GetMapping("/")
    public List<Argo16> getAllArgo16() {
        return argo16Services.getAll();
    }

    @GetMapping("/count")
    public long getArgo16Count() {
        return argo16Services.count();
    }


    @GetMapping("/removeAll")
    public String removeAllArgo16() {
        argo16Services.removeAll();
        return "done";
    }
}
