package sa.edu.kau.stu.drone_database_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import sa.edu.kau.stu.drone_base_library.entity.Drone;
import sa.edu.kau.stu.drone_base_library.path.Coord;
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

	/*
	 * Returns int[][] array of i collisions
	 * [i][0] is x coordinate
	 * [i][1] is y coordinate
	 * [i][2] is z coordinate
	 * 
	 * NOTE: time is in String format.
	 */
	
	public int[][] getAllCollisionsCoords() {
		List<Drone> drones = _droneRepo.findAll();
		List<Coord> collisions = new ArrayList<Coord>();
		
		//for each unique pair of drones
		for(int i = 0; i<drones.size(); i++) {
			for(int j = i+1; j<drones.size();) {
				
				List<Coord> path1 = drones.get(i).getPath();
				List<Coord> path2 = drones.get(j).getPath();
				
				//figure out the range of times that both drone paths are active in
				//because maybe one drone starts/stops before the other

				int startTimePath1 = path1.get(0).getTime();
				int startTimePath2 = path2.get(0).getTime();
				int stopTimePath1 = path1.get(path1.size() - 1).getTime();
				int stopTimePath2 = path2.get(path2.size() - 1).getTime();
				
				int startTime = startTimePath1;
				int stoptime = stopTimePath1;
				
				if(startTimePath2>startTimePath1)
					startTime = startTimePath2;
				if(stopTimePath2>stopTimePath1)
					startTime = stopTimePath2;
				
				
				//check for collision at each time t
				boolean collisionTrue = false;

				for(int time = startTime; time<stoptime; time++) {
					collisionTrue = isCollision(path1.get(time), path2.get(time));
					
					if(collisionTrue) { //collision found
						collisions.add(path1.get(time));
						collisions.add(path2.get(time));
						break; //since collision found, stop
					}
					
				}
				if(collisionTrue)
					break; //since collision already found between this drone pair, go to next drone pair.
			}
			
		}
		
		
		//convert List<Coord> to 2d array of coordinates x,y,z representing all instances of collisions
		
		if(collisions.size()==0)
			return null;

		
		int[][] coordinates = new int[collisions.size()][3];
		
		for(int i = 0; i < collisions.size(); i++) {
			coordinates[i][0]= collisions.get(i).getX();
			coordinates[i][1]= collisions.get(i).getY();
			coordinates[i][2]= collisions.get(i).getZ();
		}
		
		return coordinates;
		
	}
	

	/*
	 * Returns String[][] array of i collisions
	 * [i][0] is String name of drone 1
	 * [i][1] is String name of drone 2
	 * [i][2] is String time of collision 
	 * 

	 * NOTE: time is in String format.
	 */
	public String[][] getAllCollisionsDrones() {
		List<Drone> drones = _droneRepo.findAll();
		List<Drone> collisions = new ArrayList<>();
		List<Integer> times = new ArrayList<>();
		
		//for each unique pair of drones
		for(int i = 0; i<drones.size(); i++) {
			for(int j = i+1; j<drones.size();) {
				
				List<Coord> path1 = drones.get(i).getPath();
				List<Coord> path2 = drones.get(j).getPath();
				
				//figure out the range of times that both drone paths are active in
				//because maybe one drone starts/stops before the other

				int startTimePath1 = path1.get(0).getTime();
				int startTimePath2 = path2.get(0).getTime();
				int stopTimePath1 = path1.get(path1.size() - 1).getTime();
				int stopTimePath2 = path2.get(path2.size() - 1).getTime();
				
				int startTime = startTimePath1;
				int stoptime = stopTimePath1;
				
				if(startTimePath2>startTimePath1)
					startTime = startTimePath2;
				if(stopTimePath2>stopTimePath1)
					startTime = stopTimePath2;
				
				
				//check for collision at each time t
				boolean collisionTrue = false;

				for(int time = startTime; time<stoptime; time++) {
					collisionTrue = isCollision(path1.get(time), path2.get(time));
					
					if(collisionTrue) { //collision found
						collisions.add(drones.get(i));
						collisions.add(drones.get(j));
						times.add(time);
						break; //since collision found, stop
					}
					
				}
				if(collisionTrue)
					break; //since collision already found between this drone pair, go to next drone pair.
			}
			
		}
		if(collisions.size()==0)
			return null;
		
		String[][] dronePairs = new String[collisions.size()/2][3];
		for (int i = 0, d = 0, t = 0; d < collisions.size(); i++, t++,d++) {
			dronePairs[i][0] = collisions.get(d).getName();
			dronePairs[i][1] = collisions.get(d++).getName();
			dronePairs[i][2] = times.get(t).toString();
		}
		
		return dronePairs;
		
		
	}
		
	// calculate collision between two points
	private boolean isCollision(Coord coord1, Coord coord2) {
		int threshold = 5;
		
		double d = Math.pow((Math.pow(coord2.getX() - coord1.getX(), 2) +
                Math.pow(coord2.getY() - coord1.getY(), 2) +
                Math.pow(coord2.getZ() - coord1.getZ(), 2) *
                           1.0), 0.5);
		
		if(d < threshold)
			return true; //collision
		
		return false; //else no collision
		
	}
	

	public Page<Drone> getPagedDrones(int PageNumber) {
		Pageable pagable = PageRequest.of(PageNumber - 1, 8, Sort.by(Sort.Direction.ASC, "name"));
		return _droneRepo.findAll(pagable);
	}
}
