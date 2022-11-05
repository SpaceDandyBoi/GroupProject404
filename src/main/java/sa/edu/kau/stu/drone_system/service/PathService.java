package sa.edu.kau.stu.drone_system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sa.edu.kau.stu.drone_system.entity.Drone;
import sa.edu.kau.stu.drone_system.entity.Path;
import sa.edu.kau.stu.drone_system.repository.PathRepository;

@Service
public class PathService implements IPathService {
	@Autowired
	private PathRepository _pathRepo;

	public void addPath(Path path) {
		_pathRepo.save(path);
	}

	@Override
	public Path getDroneLocation(Long id, int time) {
		List<Path> paths = _pathRepo.findAll();
		for (Path p : paths) {
			if (p.getDroneID() == id && p.getTime() == time) {
				return p;
			}
		}

		return null;
	}

	@Override
	public double GetDroneSpeed(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Path> GetAllDronesLocations(int time) {
		List<Path> paths = _pathRepo.findAll();
		List<Path> ans = new ArrayList<>();
		for (Path p : paths) {
			if (p.getTime() == time) {
				ans.add(p);
			}
		}
		return ans;
	}

	@Override
	public List<Path> getDronePath(Long id) {
		List<Path> paths = _pathRepo.findAll();
		List<Path> ans = new ArrayList<>();
		for (Path p : paths) {
			if (p.getDroneID() == id) {
				ans.add(p);
			}
		}
		return ans;
	}

	@Override
	public List<Path> getAllDronesPaths() {
		return _pathRepo.findAll();
	}

	@Override
	public List<Drone> getCollisions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePath(Long id, Path updatedPath) {

		if (_pathRepo.findById(id).isPresent()) {
			Path path = _pathRepo.findById(id).get();
			path.setDroneID(updatedPath.getDroneID());
			path.setTime(updatedPath.getTime());
			path.setXcoor(updatedPath.getXcoor());
			path.setYcoor(updatedPath.getYcoor());
			path.setZcoor(updatedPath.getZcoor());
			_pathRepo.save(path);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Path getPath(Long id) {
		Optional<Path> path = _pathRepo.findById(id);
		if (path != null) {
			return path.get();
		}
		return null;
	}
}
