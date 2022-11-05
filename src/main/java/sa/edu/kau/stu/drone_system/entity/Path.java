package sa.edu.kau.stu.drone_system.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Path {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long drone_id;
	private int Xcoor;
	private int Ycoor;
	private int Zcoor;
	private int time;

	public Path() {
	}

	public Path(Long id, Long drone_id, int xcoor, int ycoor, int zcoor, int time) {
		this.id = id;
		this.drone_id = drone_id;
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

	public Long getDroneID() {
		return drone_id;
	}

	public void setDroneID(Long droneID) {
		this.drone_id = droneID;
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
