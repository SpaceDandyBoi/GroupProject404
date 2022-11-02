package sa.edu.kau.stu.drone_system.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Path {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private UUID DroneId;
	private int Xcoor;
	private int Ycoor;
	private int Zcoor;
	private int time;

	public Path() {
	}

	public Path( UUID droneId, int xcoor, int ycoor, int zcoor, int time) {
		DroneId = droneId;
		Xcoor = xcoor;
		Ycoor = ycoor;
		Zcoor = zcoor;
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getDroneId() {
		return DroneId;
	}

	public void setDroneId(UUID droneId) {
		DroneId = droneId;
	}

	public int getXcoor() {
		return Xcoor;
	}

	public void setXcoor(int xcoor) {
		Xcoor = xcoor;
	}

	public int getYcoor() {
		return Ycoor;
	}

	public void setYcoor(int ycoor) {
		Ycoor = ycoor;
	}

	public int getZcoor() {
		return Zcoor;
	}

	public void setZcoor(int zcoor) {
		Zcoor = zcoor;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
