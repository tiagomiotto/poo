package lab4;
import java.util.LinkedList;

public class StockOwner {
	protected String name;
	protected float money;
	LinkedList<Share> shares =new LinkedList<Share>();
	
	public StockOwner(String name, float money) {
		super();
		this.name = name;
		this.money = money;
	}
	
	void addShare(Share a) {}
	
	void removeShare(Share b) {}
	void credit(float amount){}
	void debit(float amount) {}
	
	
	
}
