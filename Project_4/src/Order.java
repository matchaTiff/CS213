import java.util.ArrayList;
import java.util.Arrays;

/**
 * Order class, includes many order lines which contains the sandwich, its extra
 * ingredients, and price. Each order can contain multiple of the same orderline.
 * Includes methods that add and remove orderlines and structures the orders in
 * numerical ascending order.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class Order implements Customizable {
    public static int lineNumber; //reset for each new order;
	private ArrayList<OrderLine> orderlines;

	public Order(){
		lineNumber = 1;
		this.orderlines = new ArrayList<OrderLine>();
	}

	public String printArray() {
		return Arrays.toString(orderlines.toArray());
	}

	/**
     * Adds orderline to order
     * @param obj the type of obj to be added
     * @return True if added, false if otherwise
     */
	@Override
	public boolean add(Object obj) {
		Sandwich newSandwich = (Sandwich) obj;
		OrderLine sandwichOrder = new OrderLine(lineNumber, newSandwich, newSandwich.price());
		orderlines.add(sandwichOrder);
		lineNumber++;
		// incrementLineNumber();

		
		for(OrderLine l : orderlines){
			System.out.println(l.getLineNum() + " " + l.getSandwich().toString() + " " + l.getPrice());
		}
		System.out.println();
		

		return true;
	}

	/**
     * Removes orderline from the order
     * @param obj the type of obj to be removed
     * @return True if removed, false if otherwise
     */
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

	public ArrayList<OrderLine> getOrderLines() {
		return orderlines;
	}
}
