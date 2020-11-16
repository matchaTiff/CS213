import java.util.ArrayList;

/**
 * Beef class that extends Sandwich class.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class Beef extends Sandwich {

    private final double initSandwichPrice = 10.99;

    /**
     * Default constructor
     */
    public Beef(){
        super();
        extras = new ArrayList<Extra>();
    }

    /**
     * Abstract method that returns the price for the beef sandwich
     * @return the price for the beef sandwich
     */
	@Override
	public double price() {
		return PER_EXTRA * extras.size() + initSandwichPrice;
    }
    
    /**
     * Formats the output of the sandwich details
     * @return Type of sandwich, its ingredients, and the price
     */
    @Override
    public String toString() {
        return "Beef Sandwich; Roast Beef, Provolone Cheese, Mustard, Extra: " + super.toString();
    }
    
}
