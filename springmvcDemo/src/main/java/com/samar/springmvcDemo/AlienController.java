package com.samar.springmvcDemo;

import com.samar.springmvcDemo.model.Alein;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AlienController {

    @Autowired
    AleinRepo repo;

    @GetMapping(path="aliens")
    public List<Alein> getAleins(){

        List<Alein> aleins=repo.findAll();
        return aleins;
    }
    @GetMapping("alien/{id}")
    public  Alein getAlein(@PathVariable("id") int id){
        Optional<Alein> alein=repo.findById(id);
        return alein.orElse(null);
    }
    @PostMapping(path="alien",consumes = {"application/json"})
    public Alein addAlein(@RequestBody Alein a){
        repo.save(a);
        return  a;
    }

}
