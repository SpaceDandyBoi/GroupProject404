package com.example.groupproject404.dao;

import com.example.groupproject404.entity.Drone;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface droneDAO {
    int insertDrone(UUID id, Drone drone);

    default int insertDrone(Drone drone){
        UUID id = UUID.randomUUID();
        return insertDrone(id, drone);
    }

    List<Drone> selectAlldrone();

    Optional<Drone> selectDroneByID(UUID id);

    int deleteDroneById(UUID id);

    int updateDroneById(UUID id, Drone drone);



}
