package sa.edu.kau.stu.drone_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

	// use this when collisions.html is ready
	
	  @GetMapping("/collisions/drones")
	  public String collisionsPage(Model model) {
		  //model.addAttribute("drones",myDrones.getAllCollisionsDrones());
		  model.addAttribute("drones", myDrones.getAllDrones());
		  model.addAttribute("collision", myDrones.getAllCollisionsDrones());

		  return "collisions_drones";
	  }
	  
	  @GetMapping("/collisions/coords")
	  public String collisionsCoordsPage(Model model) {
		  model.addAttribute("drones", myDrones.getAllDrones());
		  model.addAttribute("collision", myDrones.getAllCollisionsCoords());


		  return "collisions_coords";
	  }

	// Return a list of drones that would collide a given time
	@GetMapping("/drones/collisions")
	public List<Drone> collisionsPage() {
		// TODO: Implmement here
		return null;
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
	// public void postDrone(@PathVariable("id") String id, @RequestBody Drone drone) {
	// 	myDrones.updateDrone(id, drone);
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
