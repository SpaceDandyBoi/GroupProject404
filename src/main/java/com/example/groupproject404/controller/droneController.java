package com.example.groupproject404.controller;


import com.example.groupproject404.entity.Drone;
import com.example.groupproject404.service.droneSerInter;
import com.example.groupproject404.service.droneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("controller/v1/drone")
@RestController
public class droneController {
	
	@Autowired
	droneSerInter myDrones;

    @Autowired
    public droneController(droneService _droneService) {
    }

    @PostMapping
    public void addDrone(@RequestBody Drone drone){
    	myDrones.addDrone(drone);
    }

    @GetMapping
    public List<Drone> getAllDrone(){
        return myDrones.getAllDrones();
    }

    @GetMapping(path = "{id}")
    public Drone getDroneById(@PathVariable("id") long id){
        return myDrones.getDroneInfo(id);
    }

    @DeleteMapping(path = "{id}")
    public boolean deleteDroneById(@PathVariable("id") long id){
    	return myDrones.deleteDrone(id);
    }

    @PutMapping(path = "{id}")
    public boolean updateDrone(@PathVariable("id") long id, @RequestBody Drone droneToupdate){
    	return myDrones.updateDrone(id, droneToupdate);
    }
}
