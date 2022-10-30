package com.example.groupproject404.dao;

import com.example.groupproject404.entity.Drone;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class droneDataService implements droneDAO {
    @Override
    public int insertDrone(UUID id, Drone drone) {
        return 0;
    }

    @Override
    public List<Drone> selectAlldrone() {
        return List.of(new Drone(UUID.randomUUID(), "From POSTGRES DB","From POSTGRES DB","From POSTGRES DB",1));
    }

    @Override
    public Optional<Drone> selectDroneByID(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deleteDroneById(UUID id) {
        return 0;
    }

    @Override
    public int updateDroneById(UUID id, Drone drone) {
        return 0;
    }
}
