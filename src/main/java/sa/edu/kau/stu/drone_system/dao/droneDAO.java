package sa.edu.kau.stu.drone_system.dao;

import sa.edu.kau.stu.drone_system.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;


public interface droneDAO extends JpaRepository<Drone, Long> {

}
