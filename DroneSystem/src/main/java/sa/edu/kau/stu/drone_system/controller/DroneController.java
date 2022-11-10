package sa.edu.kau.stu.drone_system.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sa.edu.kau.stu.drone_base_library.entity.Drone;
import sa.edu.kau.stu.drone_base_library.path.Coord;
import sa.edu.kau.stu.drone_database_service.service.IDroneService;

@CrossOrigin
@RestController
public class DroneController {
	@Autowired
	IDroneService myDrones;

	@Autowired
	public DroneController(IDroneService _droneService) {
	}

	// **************************************************************
	// /drones
	// **************************************************************

	@GetMapping("/drones")
	public List<Drone> getDrones() {
		return myDrones.getAllDrones();
	}

	@PostMapping("/drones")
	public void postDrones(@RequestBody Drone drone) {
		myDrones.addDrone(drone);
	}

	// TODO: allow api to create a new drone list
	// @PutMapping("/drones")
	// @PatchMapping("/drones")
	// @DeleteMapping("/drones")

	// **************************************************************
	// /drones/collisions
	// **************************************************************

	// Return a list of drones that would collide a given time
	@GetMapping("/drones/collisions")
	public HashMap<String, HashMap<String,String>> collisionsPage() {
		HashMap<String, HashMap<String,String>> response = new HashMap<String, HashMap<String,String>>();

		String[][] data = myDrones.getAllCollisionsDrones();
		for(int i =0; i < data.length; i++) {
			String colideName = "Collision" + (i+1);
			HashMap<String, String> temp = new HashMap<>();
			temp.put("Drone1", data[i][0]);
			temp.put("Drone2", data[i][1]);
			temp.put("Time", data[i][2]);
			response.put(colideName, temp);
		}
		
		return response;
	}

	// **************************************************************
	// /drones/{id}
	// **************************************************************

	// this one will be deleted later:
	@GetMapping("/drones/{id}")
	public Drone getDrone(@PathVariable String id) {
		return myDrones.getDroneInfo(id);
	}

	// TODO: I don't get this
	// @PostMapping("/drones/{id}")
	// public void postDrone(@PathVariable("id") String id, @RequestBody Drone
	// drone) {
	// myDrones.updateDrone(id, drone);
	// }

	@PutMapping("/drone/{id}")
	public void putDrone(@PathVariable("id") String id, @RequestBody Drone drone) {
		myDrones.updateDrone(id, drone);
	}

	// TODO: I don't know if this is the correct way
	@PatchMapping("/drones/{id}")
	public void patchDrone(@PathVariable("id") String id, @RequestBody Drone drone) {
		myDrones.updateDrone(id, drone);
	}

	@DeleteMapping("/drones/{id}")
	public void deleteDrone(@PathVariable("id") String id) {
		myDrones.deleteDrone(id);
	}

	// **************************************************************
	// useless so far
	// **************************************************************

	@GetMapping("/drones/{id}/path")
	public List<Coord> getDronePath(@PathVariable("id") String id) {
		return myDrones.getDroneInfo(id).getPath();
	}

	@PutMapping("/drones/{id}/path")
	public boolean updateDronePath(@PathVariable("id") String id, @RequestBody Drone droneToupdate) {
		return myDrones.updateDrone(id, droneToupdate);
	}

	@GetMapping("/drones/{id}/model")
	public StringResponseWrapper getDroneModel(@PathVariable("id") String id) {
		StringResponseWrapper s = new StringResponseWrapper(myDrones.getDroneModel(id));
		return s;
	}

	@GetMapping("/drones/{id}/mass")
	public double getDroneMass(@PathVariable("id") String id) {
		return myDrones.getDroneMass(id);
	}

	@GetMapping("/drones/{id}/batteryCapacity")
	public int getDroneBatterCapacity(@PathVariable("id") String id) {
		return myDrones.getDroneBatteryCapacity(id);
	}

	@GetMapping("/drones/{id}/currentCharge")
	public double getDroneCurrentCharege(@PathVariable("id") String id) {
		return myDrones.getDroneCurrentCharge(id);
	}
}
