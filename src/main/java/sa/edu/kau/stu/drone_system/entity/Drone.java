package sa.edu.kau.stu.drone_system.entity;

import org.springframework.data.annotation.Id;

public class Drone {
	@Id
	private Long id;
	private String Name;
	private String model;
	private double mass;
	private int batteryCapacity;
	private double batteryPercentage;

	public Drone() {
	}

	public Drone(Long id, String name, String model, double mass, int batteryCap, double battPerc) {
		this.id = id;
		Name = name;
		this.model = model;
		this.mass = mass;
		this.batteryCapacity = batteryCap;
		this.batteryPercentage = battPerc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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

	public void setBatteryCapacity(int batteryCap) {
		this.batteryCapacity = batteryCap;
	}

	public double getBatteryPercentage() {
		return batteryPercentage;
	}

	public void setBatteryPercentage(double battPerc) {
		this.batteryPercentage = battPerc;
	}
}
