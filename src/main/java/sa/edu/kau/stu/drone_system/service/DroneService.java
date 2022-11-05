package sa.edu.kau.stu.drone_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sa.edu.kau.stu.drone_system.entity.Drone;
import sa.edu.kau.stu.drone_system.repository.DroneRepository;

@Service
public class DroneService implements IDroneService {
	@Autowired
	DroneRepository _droneRepo;

	@Override
	public void addDrone(Drone drone) {
		_droneRepo.save(drone);
	}

	@Override
	public List<Drone> getAllDrones() {
		return _droneRepo.findAll();
	}

	@Override
	public Drone getDroneInfo(Long id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone.isPresent()) {
			return drone.get();
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteDrone(Long id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone.isPresent()) {
			_droneRepo.delete(drone.get());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateDrone(Long id, Drone droneToupdate) {
		if (_droneRepo.findById(id).isPresent()) {
			Drone drone = _droneRepo.findById(id).get();
			drone.setName(droneToupdate.getName());
			drone.setModel(droneToupdate.getModel());
			drone.setMass(droneToupdate.getMass());
			drone.setBatteryCap(droneToupdate.getBatteryCap());
			drone.setBattPerc(droneToupdate.getBattPerc());
			_droneRepo.save(drone);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getDroneModel(Long id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone != null) {
			return drone.get().getModel();
		}

		return null;
	}

	@Override
	public double getDroneMass(Long id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone != null) {
			return drone.get().getMass();
		}

		return 0;
	}

	@Override
	public int getDroneBatteryCapacity(Long id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone != null) {
			return drone.get().getBatteryCap();
		}
		return 0;
	}

	@Override
	public double getDroneCurrentCharge(Long id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone != null) {
			return drone.get().getBattPerc();
		}
		return 0;
	}
}
