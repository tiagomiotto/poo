package circular.list;

public class NumericCircularLinkedList<T extends Number> extends CircularLinkedList<T> {
	
	public NumericCircularLinkedList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double average() {
		NodeCLL<T> aux = base.next;
		double average=base.element.doubleValue();
		int count=1;
		
		while(aux!=base) {
			average+=aux.element.doubleValue();
			count++;
			aux=aux.next;
		}
		
		return average/count;
				
	}
}
