package sa.edu.kau.stu.drone_base_library.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import sa.edu.kau.stu.drone_base_library.path.Coord;
import sa.edu.kau.stu.drone_base_library.path.PathType;

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
	
	public Drone(String name, String model, double mass, int capacity, double percentage, PathType pathType) {
		this.name = name;
		this.model = model;
		this.mass = mass;
		this.batteryCapacity = capacity;
		this.batteryPercentage = percentage;
		this.pathType = pathType;
		this.path = new ArrayList<>();
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
	
	
	//Get Coord at requested time
	public Coord getXYZValuesAtTime(int time) {
		
		for (int i = 0; i < path.size(); i++) {
			if(path.get(i).getTime() == time) {
				return path.get(i);


			}
		}
		return null;
	}
	
	public void addPointToPath(int x, int y, int z, int time) {
		
		boolean flag = false;
		for(Coord d: this.path) {
			if(d.getX() == x && d.getY() == y & d.getZ() == z && d.getTime() == time) {
				flag = true;
				break;
			}
		}
		
		if(!flag) {
			path.add(new Coord(x,y,z,time));
		}
		
	}
}
