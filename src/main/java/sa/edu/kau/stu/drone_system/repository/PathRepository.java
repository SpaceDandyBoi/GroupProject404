package sa.edu.kau.stu.drone_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import sa.edu.kau.stu.drone_system.entity.Path;

public interface PathRepository extends MongoRepository<Path, Long> {
}
