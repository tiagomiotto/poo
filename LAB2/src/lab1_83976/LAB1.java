package lab1_83976;

public class LAB1 {
	int x;
	int y;
	
	
	public LAB1(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LAB1 other = (LAB1) obj;
		if (x != other.x)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "LAB1[x=" + x + ", y=" + y + "]";
	}
	
	
	
	
}
