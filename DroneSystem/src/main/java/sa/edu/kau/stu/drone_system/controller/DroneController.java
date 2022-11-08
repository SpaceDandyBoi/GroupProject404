package sa.edu.kau.stu.drone_system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import sa.edu.kau.stu.drone_base_library.entity.Drone;
import sa.edu.kau.stu.drone_base_library.path.Coord;
import sa.edu.kau.stu.drone_database_service.service.IDroneService;

//@RequestMapping("controller/v1/drone")
@Controller
public class DroneController {
	@Autowired
	IDroneService myDrones;

	@Autowired
	public DroneController(IDroneService _droneService) {
	}

	// **************************************************************

	
	//							/
	//**************************************************************
	
	//dashboard

	@GetMapping("/")
	public String getAllDrone(Model model) {
		model.addAttribute("Drones", myDrones.getAllDrones());
		return "index";
	}

	// **************************************************************
	// /drones/
	// **************************************************************

	// list all Drones
	@GetMapping("/drones")
	public String drones(Model model) {
		return dronesPage(1, model);
	}

			
	//pages

	@GetMapping("/drones/{page}")
	public String dronesPage(@PathVariable("page") int currentPage, Model model) {
		Page<Drone> page = myDrones.getPagedDrones(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("dronesList", page.getContent());
		return "drones";
	}

	// **************************************************************
	// /drones/id
	// **************************************************************

	// use this when view_drone.html is ready

	/*
	 * @GetMapping("/drones/id/{id}")
	 * public String viewDronePage(Model model) {
	 * return "viewDrone";
	 * }
	 */

	// this one will be deleted later:
	@GetMapping(path = "/drone/{id}")
	public String getDroneById(@PathVariable String id, Model model) {
		model.addAttribute("_drone", myDrones.getDroneInfo(id));
		return ("drone");
	}

	@PutMapping(path = "update/{id}")
	public boolean updateDrone(@PathVariable("id") String id, @RequestBody Drone droneToupdate) {
		return myDrones.updateDrone(id, droneToupdate);
	}

	// **************************************************************
	// /drones/new
	// **************************************************************

	// use this when new_drone.html is ready:
	@GetMapping(path = "/drones/new")
	public String newDronePage(Model model) {
		Drone drone = new Drone();
		
		StringResponseWrapper r = new  StringResponseWrapper(1);
		model.addAttribute("drone", drone);
		model.addAttribute("pathCount", r);
		return "new_drone";
	}

	@PostMapping(path = "/drones/new")
	public String addDrone(Model model, @ModelAttribute("drone") Drone drone, @ModelAttribute("pathCount") StringResponseWrapper r) {
		
		Coord coord = new Coord();
		model.addAttribute("coord", coord);
		drone.setPath(new ArrayList());
		//myDrones.addDrone(drone);
		return addPathCheck(model, drone, r.getResponse());
	}
	
	@PostMapping(path = "/path/new/{pathCount}")
	public String addPathCheck(Model model, @ModelAttribute("drone") Drone drone, @PathVariable("Count") int pathCount) {
		System.out.println(pathCount);
		if(pathCount == 0) {
			myDrones.addDrone(drone);
			return "redirect:/drones";
		}else if(model.getAttribute("coord") != null) {
			drone.getPath().add((Coord) model.getAttribute("coord"));
			System.out.println(drone.getPath());
		}
		
		model.addAttribute("drone", drone);
		model.addAttribute("Count",pathCount-1);
		return "new_path";
	}

	// **************************************************************
	// /drones/editDrone
	// **************************************************************

	// use this when edit_drone.html is ready:
	@GetMapping("/drones/editDrone/{id}")
	public String editDronePage(@PathVariable String id, Model model) {
		model.addAttribute("drone", myDrones.getDroneInfo(id));
		return "edit_drone";
	}

	@PostMapping(path = "/drones/editDrone/{id}")
	public String editDrone(@PathVariable String id, @ModelAttribute("drone") Drone drone, Model model) {

		Drone oldDrone = myDrones.getDroneInfo(id);
		oldDrone.setId(id);
		oldDrone.setModel(drone.getModel());
		oldDrone.setName(drone.getName());
		oldDrone.setMass(drone.getMass());
		oldDrone.setBatteryCapacity(drone.getBatteryCapacity());
		oldDrone.setBatteryPercentage(drone.getBatteryPercentage());

		// What do do with path? keep it i believe.

		myDrones.updateDrone(id, oldDrone);
		return "redirect:/drones";

	}

	// **************************************************************
	// /drones/editPath
	// **************************************************************

	// use this when edit_path.html is ready:
	@GetMapping("/drones/editPath/{id}")
	public String editPathPage(@PathVariable String id, Model model) {
		// implement here
		return "edit_path";
	}

	@PostMapping("/drones/editPath/{id}")
	public String editPath(@PathVariable String id, @ModelAttribute("drone") Drone drone, Model model) {
		// implement here
		return "redirect:/drones";

	}

	// **************************************************************
	// /drones/delete
	// **************************************************************
	
	
	@GetMapping("/drones/delete/{id}")
	public String deleteDrone(@PathVariable String id) {
		myDrones.deleteDrone(id);
		return "redirect:/drones";
	}

	// **************************************************************
	// /drones/collisions
	// **************************************************************

	// use this when collisions.html is ready
	/*
	 * @GetMapping("/drones/collisions")
	 * public String collisionsPage(Model model) {
	 * return "collisions";
	 * }
	 */

	// this one will be deleted later:
	@GetMapping(path = "/drones/collisions")
	public List<Drone> collisionsPage() {
		// implmement here
		return null;
	}

	// **************************************************************
	// /map
	// **************************************************************

	// uses map.html when ready
	@GetMapping("/map")
	public String mapPage(Model model) {
		return "map";
	}

	// **************************************************************
	// /map/id/{id}
	// **************************************************************

	// uses map.html when ready
	@GetMapping("/map/id/{id}")
	public String singleDroneMap(Model model) {
		return "map"; // can it reuse map.html or we need another web page?
	}

	// **************************************************************
	// /map/time/{time}
	// **************************************************************

	// uses map.html when ready
	@GetMapping("/map/time/{time}")
	public String mapAtTime(Model model) {
		return "map"; // can it reuse map.html or we need another web page?
	}

	// **************************************************************
	// useless so far
	// **************************************************************

	// @GetMapping(path = "/drones/path/{id}")
	// public List<Coord> getDronePath(@PathVariable("id") String id) {
	// return myDrones.getDronePath(id);
	// }

	@PutMapping(path = "drones/path/{id}")
	public boolean updateDronePath(@PathVariable("id") String id, @RequestBody Drone droneToupdate) {
		return myDrones.updateDrone(id, droneToupdate);
	}

	@GetMapping(path = "drones/model/{id}")
	public void getDroneModel(@PathVariable("id") String id) {
		//StringResponseWrapper s = new StringResponseWrapper(myDrones.getDroneModel(id));
	
	}

	@GetMapping(path = "drones/mass/{id}")
	public double getDroneMass(@PathVariable("id") String id) {
		return myDrones.getDroneMass(id);
	}

	@GetMapping(path = "drones/batteryCapacity/{id}")
	public int getDroneBatterCapacity(@PathVariable("id") String id) {
		return myDrones.getDroneBatteryCapacity(id);
	}

	@GetMapping(path = "drones/currentCharge/{id}")
	public double getDroneCurrentCharege(@PathVariable("id") String id) {
		return myDrones.getDroneCurrentCharge(id);
	}
}
