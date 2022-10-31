package com.example.groupproject404.controller;

import com.example.groupproject404.entity.Drone;
import com.example.groupproject404.service.droneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("controller/v1/drone")
@RestController
public class droneController {

    @Autowired
    public droneController(droneService _droneService) {
    }

    @PostMapping
    public void addDrone(@RequestBody Drone drone){
    }

    @GetMapping
    public List<Drone> getAllDrone(){
        return null;
    }

    @GetMapping(path = "{id}")
    public Drone getDroneById(@PathVariable("id") UUID id){
        return null;
    }

    @DeleteMapping(path = "{id}")
    public void deleteDroneById(@PathVariable("id") UUID id){

    }

    @PutMapping(path = "{id}")
    public void updateDrone(@PathVariable("id") UUID id, @RequestBody Drone droneToupdate){

    }
}
