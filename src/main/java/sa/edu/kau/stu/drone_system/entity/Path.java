package sa.edu.kau.stu.drone_system.entity;

import org.springframework.data.annotation.Id;

public class Path {
	@Id
	private Long id;
	private Long drone_id;
	private int x;
	private int y;
	private int z;
	private int time;

	public Path() {
	}

	public Path(Long id, Long drone_id, int x, int y, int z, int time) {
		this.id = id;
		this.drone_id = drone_id;
		this.x = x;
		this.y = y;
		this.z = z;
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
