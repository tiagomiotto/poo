package lab1_83976;

import java.util.LinkedList;
import java.util.List;

public class LAB2 {
	private List<LAB1> labs1 = new LinkedList<LAB1>(); 
	
	public boolean associateLab1(LAB1 obj) {
		if(labs1.size()<10 && !labs1.contains(obj)) {
			labs1.add(obj);
			return true;
		}
		if(labs1.size()>=10) {
			System.out.println("Trying to associate more than 10 objects\n");
			System.exit(2);
		}
		return false;
	}

	@Override
	
	public String toString() {
		return "LAB2 [labs1=" + labs1 + "]";
	}
	
	
	
}
