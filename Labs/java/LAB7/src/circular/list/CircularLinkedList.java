package circular.list;

public class CircularLinkedList<T> {
	protected NodeCLL<T> base;
	
	public void add(T t) {
		if(base==null) {//Se não existir lista
			base= new NodeCLL<T>();
			base.next=base;
			base.element=t;
		}
		else {
			NodeCLL<T> atual = base; //Node atual
			NodeCLL<T> novo = new NodeCLL<T>(); //Node a adicionar
			novo.element=t;
			novo.next=base;
			while(atual.next!=base) { //Anda até o fim da lista
				atual=atual.next;
			}
			atual.next=novo;
		}
		//System.out.println("Added: "+ t);
	}
	
	public void remove(T t) {
		// == compara ponteiros
		//equals compara o elemento em si
		if(base==null) {
			System.out.print("Trying to remove from an empty list,exiting \n");
			System.exit(1);
		}
		
		NodeCLL<T> atual =base;
		NodeCLL<T> anterior=new NodeCLL<T>();
		NodeCLL<T> aux =new NodeCLL<T>();
		aux.element=t;
		if(base.equals(aux)) {
			aux=base;
			base=base.next;
			atual=base;
			while(atual.next!=aux) { //Troca o fim da lista
				atual=atual.next;
			}
			atual.next=aux.next;
		}
		else {
		while(!atual.equals(aux)) {
			anterior=atual;
			atual=atual.next;
			//System.out.println("["+anterior.element + " " + atual.element + " ]");
			if(atual==base) { //Se correr a lista toda e não achar
				System.out.println("Element " + t + " not found");
				break;
			}
		
		}
		if(atual!=base) {
		anterior.next=atual.next;
		//System.out.println("Removed: "+ t);
		}
		}
	}

	public CircularLinkedList() {
		super();
		base=null;
	}

	@Override
	public String toString() {
		String aux = new String();
		NodeCLL<T> atual =base.next;
		aux= "CircularLinkedList [base= " + base.element ;
		while(atual!=base) {
			aux += " next= " + atual.element;
			atual=atual.next;
		}
		aux+= " ]";
		
		return aux;
		
	}



	
	
	
	
}
