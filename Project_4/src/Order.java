import java.util.ArrayList;

public class Order implements Customizable {
    public static int lineNumber; //reset for each new order;
	private ArrayList<OrderLine> orderlines;

	static int incrementLineNumber() {
		return lineNumber++;
	}

	static int decrementLineNumber() {
		return lineNumber--;
	}

	public int getLineNumber() {
		return lineNumber;
	}
    
	@Override
	public boolean add(Object obj) {
		OrderLine sandwichOrder = (OrderLine) obj;
		orderlines.add(sandwichOrder);
		lineNumber++;
		// incrementLineNumber();
        return true;
	}

	@Override
	public boolean remove(Object obj) {
		if(orderlines.isEmpty()) {
			return false;
		}
		else if(!orderlines.isEmpty()) {
			OrderLine line = (OrderLine) obj;
			orderlines.remove(line);
			lineNumber++;
			// decrementLineNumber();
        	return true;
		}
		else {
			return false;
		}
	}
}
