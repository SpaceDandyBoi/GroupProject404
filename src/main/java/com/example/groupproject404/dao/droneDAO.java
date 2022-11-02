package com.example.groupproject404.dao;

import com.example.groupproject404.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;


public interface droneDAO extends JpaRepository<Drone, Long> {

}
