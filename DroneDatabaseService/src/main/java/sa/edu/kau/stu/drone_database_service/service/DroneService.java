package sa.edu.kau.stu.drone_database_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import sa.edu.kau.stu.drone_base_library.entity.Drone;
import sa.edu.kau.stu.drone_database_service.repository.DroneRepository;

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
	public Drone getDroneInfo(String id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone.isPresent()) {
			return drone.get();
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteDrone(String id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone.isPresent()) {
			_droneRepo.delete(drone.get());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateDrone(String id, Drone update) {
		var drone = _droneRepo.findById(id);
		if (drone.isPresent()) {
			var d = drone.get();
			d.setName(update.getName());
			d.setModel(update.getModel());
			d.setMass(update.getMass());
			d.setBatteryCapacity(update.getBatteryCapacity());
			d.setBatteryPercentage(update.getBatteryPercentage());
			d.setPathType(update.getPathType());
			d.setPath(update.getPath());
			_droneRepo.save(d);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getDroneModel(String id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone != null) {
			return drone.get().getModel();
		}

		return null;
	}

	@Override
	public double getDroneMass(String id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone != null) {
			return drone.get().getMass();
		}

		return 0;
	}

	@Override
	public int getDroneBatteryCapacity(String id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone != null) {
			return drone.get().getBatteryCapacity();
		}
		return 0;
	}

	@Override
	public double getDroneCurrentCharge(String id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone != null) {
			return drone.get().getBatteryPercentage();
		}
		return 0;
	}

	@Override
	public int[] getDroneXValues(String id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone.isPresent()) {
			return drone.get().getXValues();
		}
		return new int[] {};
	}

	@Override
	public int[] getDroneYValues(String id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone.isPresent()) {
			return drone.get().getYValues();
		}
		return new int[] {};
	}

	@Override
	public int[] getDroneZValues(String id) {
		Optional<Drone> drone = _droneRepo.findById(id);
		if (drone.isPresent()) {
			return drone.get().getZValues();
		}
		return new int[] {};
	}

	public Page<Drone> getPagedDrones(int PageNumber) {
		Pageable pagable = PageRequest.of(PageNumber - 1, 8, Sort.by(Sort.Direction.ASC, "name"));
		return _droneRepo.findAll(pagable);
	}
}
