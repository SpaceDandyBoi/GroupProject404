package sa.edu.kau.stu.drone_database_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import sa.edu.kau.stu.drone_database_service.entity.Drone;

public interface DroneRepository extends MongoRepository<Drone, Long> {
}