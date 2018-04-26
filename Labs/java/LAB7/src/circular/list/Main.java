package circular.list;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NumericCircularLinkedList<Double> list = new NumericCircularLinkedList<Double>();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(true) {
		System.out.println("Add number to the list");
		Double num = scan.nextDouble();
		list.add(num);
		System.out.println("Remove a number from the list");
		num = scan.nextDouble();
		list.remove(num);
		System.out.println(list.toString());
		System.out.println("Average = " + list.average());
		}
		
	}

}
