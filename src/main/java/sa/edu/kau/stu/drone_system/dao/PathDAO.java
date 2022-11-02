package sa.edu.kau.stu.drone_system.dao;

import sa.edu.kau.stu.drone_system.entity.Path;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathDAO extends JpaRepository<Path, Long> {
}
