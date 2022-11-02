package com.example.groupproject404.service;

import java.util.List;

import com.example.groupproject404.entity.Drone;



public interface droneSerInter {
	
	void addDrone(Drone drone);
	
	List<Drone> getAllDrones();
	
	Drone getDroneInfo(Long id);
	
	boolean deleteDrone(Long id);
	
	boolean updateDrone(Long id, Drone droneToupdate);
	
}
