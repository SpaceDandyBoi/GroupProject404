package sa.edu.kau.stu.drone_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sa.edu.kau.stu.drone_system.entity.Path;

public interface PathDAO extends JpaRepository<Path, Long> {
}