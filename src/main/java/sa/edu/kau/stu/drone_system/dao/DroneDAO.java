package sa.edu.kau.stu.drone_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sa.edu.kau.stu.drone_system.entity.Drone;

public interface DroneDAO extends JpaRepository<Drone, Long> {
}
