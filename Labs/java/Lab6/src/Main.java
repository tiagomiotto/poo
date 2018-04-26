
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Image imagem = new Image(20,20);
		Retangulo ret= new Retangulo(2,2,3,4);
		Retangulo ret2= new Retangulo(6,6,5,6);
		
		imagem.add_shape(ret);
		System.out.println(imagem.toString());
		imagem.add_shape(ret2);
		System.out.println(imagem.toString());
		
	}

}
