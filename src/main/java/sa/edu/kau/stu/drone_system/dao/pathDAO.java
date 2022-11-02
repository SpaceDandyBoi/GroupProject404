package sa.edu.kau.stu.drone_system.dao;

import sa.edu.kau.stu.drone_system.entity.path;
import org.springframework.data.jpa.repository.JpaRepository;

public interface pathDAO extends JpaRepository<path, Long> {
}
