package sa.edu.kau.stu.drone_system.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import sa.edu.kau.stu.drone_base_library.entity.Drone;
import sa.edu.kau.stu.drone_base_library.path.PathType;
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
		model.addAttribute("collision", myDrones.getAllCollisionsCoords());
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

	@GetMapping("/view/collisions/drones")
	public String collisionsPage(Model model) {
		model.addAttribute("drones", myDrones.getAllDrones());
		model.addAttribute("collision", myDrones.getAllCollisionsDrones());

		return "collisions_drones";
	}

	@GetMapping("/view/collisions/coords")
	public String collisionsCoordsPage(Model model) {
		model.addAttribute("drones", myDrones.getAllDrones());
		model.addAttribute("collision", myDrones.getAllCollisionsCoords());

		return "collisions_coords";
	}

	// **************************************************************
	// /newdrone
	// **************************************************************

	// Open the new drone form page
	@GetMapping("/newdrone")
	public String newDronePage(Model model) {
		Drone drone = new Drone();
		drone.setPath(new ArrayList<>());
		model.addAttribute("drone", drone);
		return "new_drone";
	}

	// Open the new drone form page
	@PostMapping("/newdrone")
	public String newDrone(Model model, @RequestBody String s) {
		String[] ans = s.split("&");
		for (int i = 0; i < ans.length; i++) {
			ans[i] = ans[i].substring(ans[i].indexOf('=') + 1, ans[i].length());

		}

		Drone d;
		if (ans[5] == "Simple") {
			d = new Drone(ans[0], ans[1], Double.parseDouble(ans[2]), Integer.parseInt(ans[3]),
					Double.parseDouble(ans[4]), PathType.Simple);
		} else {
			d = new Drone(ans[0], ans[1], Double.parseDouble(ans[2]), Integer.parseInt(ans[3]),
					Double.parseDouble(ans[4]), PathType.Bezier);
		}

		for (int i = 10; i < ans.length; i += 4) {
			int x = Integer.parseInt(ans[i]);
			int y = Integer.parseInt(ans[i + 1]);
			int z = Integer.parseInt(ans[i + 2]);
			int t = Integer.parseInt(ans[i + 3]);
			d.addPointToPath(x, y, z, t);
		}

		myDrones.addDrone(d);
		return "redirect:/view/all";
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
		return "redirect:/view/all";
	}

	// **************************************************************
	// /deletedrone/{id}
	// **************************************************************

	
	@GetMapping("/deletedrone/{id}")
	public String deleteDronePage(@PathVariable String id) {
		if (id == null || id.equals("")) {
			throw new RuntimeException("ID `" + id + "` is invalid.");
		}

		myDrones.deleteDrone(id);
		return "redirect:/view/all";
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
