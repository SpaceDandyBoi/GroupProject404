package sa.edu.kau.stu.drone_system.service;

import sa.edu.kau.stu.drone_system.dao.DroneDAO;
import sa.edu.kau.stu.drone_system.entity.Drone;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService implements IDroneService {
	@Autowired
    DroneDAO _droneDao;


	@Override
	public void addDrone(Drone drone) {
		_droneDao.save(drone);

	}

	@Override
	public List<Drone> getAllDrones() {
		return _droneDao.findAll();
	}

	@Override
	public Drone getDroneInfo(Long id) {

		Optional<Drone> drone = _droneDao.findById(id);
		if(drone.isPresent()) {
			return drone.get();
		}else {
			return null;
		}
	}

	@Override
	public boolean deleteDrone(Long id) {

			Optional<Drone> drone = _droneDao.findById(id);
			if(drone.isPresent()) {
				_droneDao.delete(drone.get());
				return true;
			}else {
				return false;
			}

	}

	@Override
	public boolean updateDrone(Long id, Drone droneToupdate) {

			if(_droneDao.findById(id).isPresent()) {
				Drone drone = _droneDao.findById(id).get();
				drone.setName(droneToupdate.getName());
				drone.setModel(droneToupdate.getModel());
				drone.setMass(droneToupdate.getMass());
				drone.setBatteryCap(droneToupdate.getBatteryCap());
				drone.setBattPerc(droneToupdate.getBattPerc());
				_droneDao.save(drone);
				return true;
			}else {
				return false;
			}

	}
}
