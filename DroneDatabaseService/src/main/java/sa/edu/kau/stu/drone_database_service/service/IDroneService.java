package sa.edu.kau.stu.drone_database_service.service;

import java.util.List;

import sa.edu.kau.stu.drone_base_library.entity.Drone;

public interface IDroneService {
	void addDrone(Drone drone);

	List<Drone> getAllDrones();

	Drone getDroneInfo(Long id);

	boolean deleteDrone(Long id);

	boolean updateDrone(Long id, Drone droneToupdate);

	String getDroneModel(Long id);

	double getDroneMass(Long id);

	int getDroneBatteryCapacity(Long id);

	double getDroneCurrentCharge(Long id);

}
