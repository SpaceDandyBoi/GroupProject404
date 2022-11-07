package sa.edu.kau.stu.drone_base_library.path;

public class Coord {
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
