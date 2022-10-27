package com.dronesProject.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dronesProject.entity.drone;

public interface droneDAO extends JpaRepository<drone, Integer>{

}
