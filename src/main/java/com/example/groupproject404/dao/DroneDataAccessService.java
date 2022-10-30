package com.example.groupproject404.dao;

import com.example.groupproject404.entity.Drone;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class DroneDataAccessService implements droneDAO {

    private static List<Drone> DB = new ArrayList<>();
    @Override
    public int insertDrone(UUID id, Drone _drone) {
        DB.add(new Drone(id,_drone.getName(),_drone.getModel(), _drone.getDirTable(),_drone.getSpeed()));
        return 1;
    }

    @Override
    public List<Drone> selectAlldrone() {
        return DB;
    }

    @Override
    public Optional<Drone> selectDroneByID(UUID id) {
        return DB.stream()
                .filter(drone -> drone.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteDroneById(UUID id) {
        Optional<Drone> _retDrone = selectDroneByID(id);
        if (_retDrone.isEmpty()){
            return 0;
        }
        DB.remove(_retDrone.get());
        return 1;
    }

    @Override
    public int updateDroneById(UUID id, Drone _drone) {
        return selectDroneByID(id)
                .map(drone -> {
                    int indexOfDrone = DB.indexOf(drone);
                    if (indexOfDrone >= 0){
                        DB.set(indexOfDrone, new Drone(id,_drone.getName(),_drone.getModel(), _drone.getDirTable(),_drone.getSpeed()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

}
