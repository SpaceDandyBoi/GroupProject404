package sa.edu.kau.stu.path_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sa.edu.kau.stu.drone_base_library.path.Coord;
import sa.edu.kau.stu.drone_base_library.path.PathType;

@Service
public class PathService implements IPathService {
	@Override
	public Coord getCoordinate(List<Coord> path, PathType type, int time) {
		switch (type) {
			case Simple:
				return getCoordinateSimple(path, time);
			case Bezier:
				return getCoordinateBeziar(path, time);
			default:
				throw new RuntimeException("Illegal PathType (" + type + ") encountered in getCoordinate.");
		}
	}

	private Coord getCoordinateSimple(List<Coord> path, int time) {
		for (Coord coord : path) {
			if (coord.getTime() == time) {
				return coord;
			}
		}
		return null;
	}

	private Coord getCoordinateBeziar(List<Coord> path, int time) {
		// Normalize time between 0 and 1
		var time_1 = (double) path.get(0).getTime();
		var time_2 = (double) path.get(path.size() - 1).getTime();
		var norm_time = (time - Math.min(time_1, time_2)) / (Math.max(time_1, time_2) - Math.min(time_1, time_2));

		// Calculate coefficients
		var coeff = new double[path.size()];
		var n = coeff.length - 1;
		for (int i = 0; i < coeff.length; i++) {
			coeff[i] = Math.pow(1 - norm_time, n - i) * Math.pow(norm_time, i) * binomialCoefficient(n, i);
		}

		// Calculate coordinate
		var ret = new Coord(1, 1, 1, time);
		for (int i = 0; i < coeff.length; i++) {
			ret.setX((int) (ret.getX() * coeff[i] * path.get(i).getX()));
			ret.setY((int) (ret.getY() * coeff[i] * path.get(i).getY()));
			ret.setZ((int) (ret.getZ() * coeff[i] * path.get(i).getZ()));
		}

		return ret;
	}

	private double binomialCoefficient(double n, double k) {
		return factorial(n) / (factorial(k) * factorial(n - k));
	}

	private double factorial(double n) {
		var ret = 1;
		for (int i = 2; i <= n; i++) {
			ret *= i;
		}
		return ret;
	}
}
