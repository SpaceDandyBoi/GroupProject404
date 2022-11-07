package sa.edu.kau.stu.path_service.service;

import java.util.List;

import sa.edu.kau.stu.drone_base_library.path.Coord;
import sa.edu.kau.stu.drone_base_library.path.PathType;

public interface IPathService {
	public Coord getCoordinate(List<Coord> path, PathType type, int time);
}
