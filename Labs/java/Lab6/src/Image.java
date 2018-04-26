import java.util.LinkedList;

public class Image {
	private int altura;
	private int largura;
	private LinkedList<Shape> shapes = new LinkedList<Shape>();
	
	public Image(int altura, int largura) {
		super();
		this.altura = altura;
		this.largura = largura;
	}
	
	public void add_shape(Shape a) {
		shapes.add(a);
		//System.out.println("Added a shape");
		
	}
	
	public String line(int y) {
	int i,j;
	int[][] filling = new int[shapes.size()][largura]; 
	char[] line = new char[largura];
	for(i=0;i<shapes.size();i++) {
		filling[i]=new int[largura];
		filling[i]=shapes.get(i).intersection(y);
	}
	for(j=0;j<shapes.size();j++) {
	for(i=0;i<filling[j].length;i++) {
		if(filling[j][i]==0) break;
		line[filling[j][i]]= '$';
		
	}
	for(i=0;i<largura;i++) {
		if(line[i]!='$') line[i]='_';
	}
	}

	return new String(line);
	}

	@Override
	public String toString() {
		String aux = new String();
		int i;
		for(i=0;i<altura;i++) {
			aux = new StringBuilder(aux).append(line(i)).append("\n").toString();
		}
		
		return aux;
	}

	
}
