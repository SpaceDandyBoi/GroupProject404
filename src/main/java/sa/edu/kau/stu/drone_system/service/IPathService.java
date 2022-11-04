package sa.edu.kau.stu.drone_system.service;

import java.util.List;

import sa.edu.kau.stu.drone_system.entity.Drone;
import sa.edu.kau.stu.drone_system.entity.Path;

public interface IPathService {
	
	void addPath(Path path);
	
	Path getDroneLocation (Long id, int time);
	
	Path getPath(Long id);
	
	double GetDroneSpeed (Long id);
	
	List<Path> GetAllDronesLocations(int time);
	
	List<Path> getDronePath(Long id);
	
	List<Path> getAllDronesPaths();
	
	List<Drone> getCollisions();
	
	boolean updatePath(Long id, Path updatedPath);
}
