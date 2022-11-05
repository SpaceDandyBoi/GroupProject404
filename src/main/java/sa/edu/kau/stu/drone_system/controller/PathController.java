package sa.edu.kau.stu.drone_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sa.edu.kau.stu.drone_system.entity.Path;
import sa.edu.kau.stu.drone_system.service.IPathService;

@RequestMapping("controller/v1/path")
@RestController
public class PathController {
	@Autowired
	IPathService myPaths;

	@PostMapping
	public void addPath(@RequestBody Path path) {
		myPaths.addPath(path);
	}

	@GetMapping
	public List<Path> getAllPaths() {
		return myPaths.getAllDronesPaths();
	}

	@GetMapping(path = "/{id}")
	public Path getPathById(@PathVariable("id") long id) {
		return myPaths.getPath(id);
	}

	@GetMapping(path = "drone/{id}")
	public List<Path> getPathByDroneId(@PathVariable("id") long droneID) {
		return myPaths.getDronePath(droneID);
	}

	// @DeleteMapping(path = "{id}")
	// public boolean deleteDroneById(@PathVariable("id") long id) {
	// return myDrones.deleteDrone(id);
	// }

	@PutMapping(path = "{id}")
	public boolean updateDrone(@PathVariable("id") long pathID, @RequestBody Path updatedPath) {
		return myPaths.updatePath(pathID, updatedPath);
	}
}
