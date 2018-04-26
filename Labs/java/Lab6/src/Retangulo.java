
public class Retangulo extends Shape {
	
	private int altura;
	private int largura;
	
	public Retangulo(int x, int y,int h, int l) {
		super(x, y);
		this.altura=h;
		this.largura=l;
		// TODO Auto-generated constructor stub
	}

	@Override
	int[] intersection(int y) {
		int i;
		int[] pos= new int[this.largura];
		
		if(y==this.y || y== (this.y+this.altura-1)){
			
			for(i=0;i<this.largura;i++) {
				pos[i]=this.x+i;
			}
			
		}else if(y>this.y && y<(this.y+altura-1)) {
			pos[0]=this.x;
			pos[1]=this.x+this.largura-1;
		}
		
	return pos;	
	}

}
