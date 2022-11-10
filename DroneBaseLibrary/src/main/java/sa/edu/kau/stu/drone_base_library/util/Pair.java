package sa.edu.kau.stu.drone_base_library.util;

public class Pair<S, T> {
	private S item1;
	private T item2;

	public Pair(S item1, T item2) {
		this.item1 = item1;
		this.item2 = item2;
	}

	public S getItem1() {
		return item1;
	}

	public void setItem1(S item1) {
		this.item1 = item1;
	}

	public T getItem2() {
		return item2;
	}

	public void setItem2(T item2) {
		this.item2 = item2;
	}
}
