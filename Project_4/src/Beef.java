import java.util.ArrayList;

public class Beef extends Sandwich {
    private static final double INIT_PRICE = 10.99;

    /**
     * Default constructor
     */
    public Beef() {
        super();
        extras = new ArrayList<Extra>();
    }

    /**
     * Abstract method that returns the price for the beef sandwich
     * @return the price for the beef sandwich
     */
	@Override
	public double price() {
		return INIT_PRICE + (extras.size() * PER_EXTRA);
    }
    
    /**
     * Formats the output of the sandwich details
     * @return Type of sandwich, its ingredients, and the price
     */
    @Override
    public String toString() {
        return "Beef Sandwich; Roast Beef, Provolone Cheese, Mustard, Extra: " + super.toString() + price();
    }
    
}
