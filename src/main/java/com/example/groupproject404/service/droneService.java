package com.example.groupproject404.service;

import com.example.groupproject404.dao.droneDAO;
import com.example.groupproject404.entity.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class droneService {

    private final droneDAO _droneDao;

    @Autowired
    public droneService(@Qualifier("postgres") droneDAO _droneDao) {
        this._droneDao = _droneDao;
    }

    public int addDrone(Drone drone){
        return _droneDao.insertDrone(drone);
    }

    public List<Drone> getAllDrone(){
        return _droneDao.selectAlldrone();
    }

    public Optional<Drone> getDroneById(UUID id){
        return _droneDao.selectDroneByID(id);
    }

    public int deleteDrone(UUID id){
        return _droneDao.deleteDroneById(id);
    }

    public int updateDrone(UUID id, Drone drone){
        return _droneDao.updateDroneById(id, drone);
    }

}
