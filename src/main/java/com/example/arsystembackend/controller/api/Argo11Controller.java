package com.example.arsystembackend.controller.api;

import com.example.arsystembackend.entity.Argo11;
import com.example.arsystembackend.service.Argo11Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/argo11")
public class Argo11Controller {

    private Argo11Services argo11Services;

    @Autowired
    public Argo11Controller(Argo11Services argo11Services) {
        this.argo11Services = argo11Services;
    }

    @PostMapping("/")
    public String saveNewArgo11(@RequestBody List<Argo11> argo11List) {
        argo11Services.saveData(argo11List);
        return "done";
    }

    @GetMapping("/")
    public List<Argo11> getAllArgo11() {
        return argo11Services.getAll();
    }

    @GetMapping("/count")
    public int getArgo11Count() {
        return argo11Services.getAll().size();
    }


    @GetMapping("/removeAll")
    public String removeAllArgo11() {
        argo11Services.removeAll();
        return "done";
    }
}
