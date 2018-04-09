package lab4;

public class Corporation extends StockOwner {
	private float value;

	public Corporation(String name, float money, float value) {
		super(name, money);
		this.value = value;
	}
	
	public void createShares(int number) {
		if(shares.isEmpty()) {
			Share aux = new Share(number,this,this);
			shares.add(aux);
		}
		else {
			shares.getFirst().quantity+=number;
		}

	}

	public float getValue() {
		return value;
	}

	

	
}
