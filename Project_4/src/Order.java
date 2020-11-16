import java.util.ArrayList;

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

	/**
	 * Default Constructor
	 */
	public Order(){
		lineNumber = 1;
		this.orderlines = new ArrayList<OrderLine>();
	}

	/**
     * Adds orderline to order
     * @param obj the type of obj to be added
     * @return True if added, false if otherwise
     */
	@Override
	public boolean add(Object obj) {
		Sandwich newSandwich = (Sandwich) obj;
		OrderLine sandwichOrder = new OrderLine(orderlines.size()+1, newSandwich, newSandwich.price());
		orderlines.add(sandwichOrder);
		lineNumber++;

		// for(OrderLine l : orderlines){
		// 	System.out.println(l.getLineNum() + " " + l.getSandwich().toString() + " " + l.getPrice());
		// }
		// System.out.println();
		
		return true;
	}

	/**
     * Removes orderline from the order
     * @param obj the type of obj to be removed
     * @return True if removed, false if otherwise
     */
	@Override
	public boolean remove(Object obj) {
		int removeIndex = (int) obj + 1;
		if(orderlines.isEmpty()) {
			return false;
		}
		else if(!orderlines.isEmpty()) {
			boolean removed = false;
			for(OrderLine line : orderlines){
				if(line.getLineNum() == removeIndex){
					orderlines.remove(line);
					removed = true;
					if( removeIndex > orderlines.size() ){
						return true;
					}else if( removeIndex == 1 ){
						break;
					}
				}
			}

			for(OrderLine line : orderlines){
				if( line.getLineNum() < removeIndex + 1 ){
					continue;
				}
				if(removed == true){
					int newNum = line.getLineNum() - 1;
					line.setLineNum( newNum );
				}
			}

			lineNumber--;
        	return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @return orderlines array
	 */
	public ArrayList<OrderLine> getOrderLines() {
		return orderlines;
	}

	public ArrayList<OrderLine> getOrderlines(){
		return orderlines;
	}

}
