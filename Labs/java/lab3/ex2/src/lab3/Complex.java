package lab3;

public class Complex {
	int real;
	int imag;
	
	
	
	public Complex(int real, int imag) {
		super();
		this.real = real;
		this.imag = imag;
	}
	

	public Complex() {
		super();
		this.real = 0;
		this.imag = 0;
	}


	@Override
	public String toString() {
		return real + "+" + imag + "i";
	}
	
	public Complex add(Complex a, Complex b) {
		
		Complex result = new Complex();
		result.real=a.real+b.real;
		result.imag=a.imag+b.imag;
		
		return result;
	}
	public Complex subtract(Complex a, Complex b) {
		Complex result = new Complex();
		result.real=a.real-b.real;
		result.imag=a.imag-b.imag;
		
		return result;
	}
	
	public Complex multiply(Complex a, Complex b) {
		Complex result = new Complex();
		result.real=a.real*b.real-a.imag*b.imag;
		result.imag=a.imag*b.imag+a.real*b.real;
		
		return result;
	}


	@Override
	//hash code method overridden to maintain the condition
	//that 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + imag;
		result = prime * result + real;
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
		Complex other = (Complex) obj;
		if (imag != other.imag)
			return false;
		if (real != other.real)
			return false;
		return true;
	}
	
	
	
	
}
