package main;
import lab3.Complex;



public class Main {
	public static void main(String[] args) {
		Complex a = new Complex();
		Complex b = new Complex();
		Complex result = new Complex();
		
		result = result.add(a, b);
		System.out.println(result.toString());
		
		result = result.subtract(a, b);
		System.out.println(result.toString());

		result = result.multiply(a, b);
		System.out.println(result.toString());
		
		System.out.println("a==b: " + (a==b));
		System.out.println("a.equals(b): " + (a.equals(b)));
	
		
	}
}
