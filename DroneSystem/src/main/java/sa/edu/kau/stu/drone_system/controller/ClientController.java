package sa.edu.kau.stu.drone_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sa.edu.kau.stu.drone_base_library.entity.Drone;
import sa.edu.kau.stu.drone_database_service.service.IDroneService;

@Controller
public class ClientController {
	@Autowired
	IDroneService myDrones;

	@Autowired
	public ClientController(IDroneService _droneService) {
	}

	// ***************************************************************
	// /
	// ***************************************************************

	// Main dashboard
	@GetMapping("/")
	public String getAllDrone(Model model) {
		model.addAttribute("Drones", myDrones.getAllDrones());
		return "index";
	}

	// **************************************************************
	// /view/all
	// **************************************************************

	// Page that shows all drones in different pages
	@GetMapping("/view/all")
	public String drones(Model model) {
		return dronesPage(model, 1);
	}

	// Open a specific page of the drone list
	@GetMapping("/view/all/page/{page}")
	public String dronesPage(Model model, @PathVariable("page") int page) {
		if (page < 0) {
			throw new RuntimeException("Page `" + page + "` is invalid.");
		}

		var p = myDrones.getPagedDrones(page);
		var totalPages = p.getTotalPages();
		var totalItems = p.getTotalElements();

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("dronesList", p.getContent());
		return "drones";
	}

	// **************************************************************
	// /view/drone/{id}
	// **************************************************************

	// show a single drone
	@GetMapping(path = "/view/drone/{id}")
	public String getDroneById(Model model, @PathVariable String id) {
		if (id == null || id.equals("")) {
			throw new RuntimeException("ID `" + id + "` is invalid.");
		}

		model.addAttribute("_drone", myDrones.getDroneInfo(id));
		return "drone";
	}

	// **************************************************************
	// /view/map
	// **************************************************************

	// uses map.html when ready
	@GetMapping("/view/map")
	public String mapPage(Model model) {
		// TODO: Implemnt This
		return "map";
	}

	// **************************************************************
	// /map/id/{id}
	// **************************************************************

	// uses map.html when ready
	@GetMapping("/view/map/id/{id}")
	public String singleDroneMap(Model model) {
		// TODO: Implemnt This
		return "map"; // can it reuse map.html or we need another web page?
	}

	// **************************************************************
	// /map/time/{time}
	// **************************************************************

	// uses map.html when ready
	@GetMapping("/view/map/time/{time}")
	public String mapAtTime(Model model) {
		// TODO: Implemnt This
		return "map"; // can it reuse map.html or we need another web page?
	}

	// **************************************************************
	// /view/collisions
	// **************************************************************

	// use this when collisions.html is ready
	/*
	 * @GetMapping("/drones/collisions")
	 * public String collisionsPage(Model model) {
	 * return "collisions";
	 * }
	 */

	// **************************************************************
	// /newdrone
	// **************************************************************

	// Open the new drone form page
	@GetMapping("/newdrone")
	public String newDronePage(Model model) {
		return "new_drone";
	}

	// **************************************************************
	// /editDrone/{id}
	// **************************************************************

	// use this when edit_drone.html is ready:
	@GetMapping("/editdrone/{id}")
	public String editDronePage(Model model, @PathVariable String id) {
		if (id == null || id.equals("")) {
			throw new RuntimeException("ID `" + id + "` is invalid.");
		}

		model.addAttribute("drone", myDrones.getDroneInfo(id));
		return "edit_drone";
	}

	@PostMapping("/editDrone/{id}")
	public String editDrone(Model model, @PathVariable String id, @ModelAttribute("drone") Drone newDrone) {
		if (id == null || id.equals("")) {
			throw new RuntimeException("ID `" + id + "` is invalid.");
		}

		if (newDrone == null) {
			throw new RuntimeException("Drone data `" + newDrone + "` is invalid.");
		}

		var drone = myDrones.getDroneInfo(id);
		drone.setId(id);
		drone.setModel(newDrone.getModel());
		drone.setName(newDrone.getName());
		drone.setMass(newDrone.getMass());
		drone.setBatteryCapacity(newDrone.getBatteryCapacity());
		drone.setBatteryPercentage(newDrone.getBatteryPercentage());
		// What do do with path? keep it i believe.

		myDrones.updateDrone(id, drone);
		return "redirect:/drones";
	}

	// **************************************************************
	// /deletedrone/{id}
	// **************************************************************

	@GetMapping("/deletdrone/{id}")
	public String deleteDronePage(@PathVariable String id) {
		if (id == null || id.equals("")) {
			throw new RuntimeException("ID `" + id + "` is invalid.");
		}

		myDrones.deleteDrone(id);
		return "redirect:/drones";
	}

	// TODO: IMPLEMENT THIS
	// **************************************************************
	// /editpath/{id}
	// **************************************************************

	// use this when edit_path.html is ready:
	@GetMapping("/editPath/{id}")
	public String editPathPage(Model model, @PathVariable String id) {
		if (id == null || id.equals("")) {
			throw new RuntimeException("ID `" + id + "` is invalid.");
		}

		// TODO: Implement here
		return "edit_path";
	}

	@PostMapping("/editpath/{id}")
	public String editPath(Model model, @PathVariable String id, @ModelAttribute("drone") Drone drone) {
		if (id == null || id.equals("")) {
			throw new RuntimeException("ID `" + id + "` is invalid.");
		}

		if (drone == null) {
			throw new RuntimeException("Drone data `" + drone + "` is invalid.");
		}

		// TODO: Implement here
		return "redirect:/drones";
	}
}
