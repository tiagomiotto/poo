
public abstract class Shape {
	protected int x;
	protected int y;
	
	public Shape(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	abstract int[] intersection(int y);
	
	
	
}
