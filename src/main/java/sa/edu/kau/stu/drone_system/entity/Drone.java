package sa.edu.kau.stu.drone_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Drone {


	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String Name;
	private String model;
	private double mass;
	private int batteryCap;
	private double battPerc;

	public Drone() {
	}

	public Drone(Long id, String name, String model, double mass, int batteryCap, double battPerc) {
		this.id = id;
		Name = name;
		this.model = model;
		this.mass = mass;
		this.batteryCap = batteryCap;
		this.battPerc = battPerc;
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

	public int getBatteryCap() {
		return batteryCap;
	}

	public void setBatteryCap(int batteryCap) {
		this.batteryCap = batteryCap;
	}

	public double getBattPerc() {
		return battPerc;
	}

	public void setBattPerc(double battPerc) {
		this.battPerc = battPerc;
	}
}
