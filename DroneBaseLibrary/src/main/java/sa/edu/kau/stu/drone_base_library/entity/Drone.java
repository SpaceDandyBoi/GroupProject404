package sa.edu.kau.stu.drone_base_library.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Drone {
	@Id
	private String id;
	private String name;
	private String model;
	private double mass;
	private int batteryCapacity;
	private double batteryPercentage;
	private PathType pathType = PathType.Simple;
	private List<Coord> path;

	public Drone() {
	}

	public Drone(String id, String name, String model, double mass, int capacity, double percentage, PathType pathType,
			List<Coord> path) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.mass = mass;
		this.batteryCapacity = capacity;
		this.batteryPercentage = percentage;
		this.pathType = pathType;
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(int capacity) {
		this.batteryCapacity = capacity;
	}

	public double getBatteryPercentage() {
		return batteryPercentage;
	}

	public void setBatteryPercentage(double percentage) {
		this.batteryPercentage = percentage;
	}

	public PathType getPathType() {
		return pathType;
	}

	public void setPathType(PathType pathType) {
		this.pathType = pathType;
	}

	public List<Coord> getPath() {
		return path;
	}

	public void setPath(List<Coord> path) {
		this.path = path;
	}

	public int[] getXValues() {
		var ret = new int[path.size()];
		for (int i = 0; i < path.size(); i++) {
			ret[i] = path.get(i).getX();
		}
		return ret;
	}

	public int[] getYValues() {
		var ret = new int[path.size()];
		for (int i = 0; i < path.size(); i++) {
			ret[i] = path.get(i).getY();
		}
		return ret;
	}

	public int[] getZValues() {
		var ret = new int[path.size()];
		for (int i = 0; i < path.size(); i++) {
			ret[i] = path.get(i).getZ();
		}
		return ret;
	}
}

enum PathType {
	Simple,
	Bezier,
}

class Coord {
	private int x;
	private int y;
	private int z;
	private int time;

	public Coord() {
	}

	public Coord(int x, int y, int z, int time) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.time = time;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
