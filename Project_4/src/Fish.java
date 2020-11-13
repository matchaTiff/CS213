import java.util.ArrayList;

public class Fish extends Sandwich {
    private static final double INIT_PRICE = 12.99;

    /**
     * Default constructor
     */
    public Fish() {
        super();
        extras = new ArrayList<Extra>();
    }

    /**
     * Abstract method that returns the price for the fish sandwich
     * @return the price for the fish sandwich
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
        return "Fish Sandwich; Grilled Snapper, Cilantro, Lime, Extra: " + super.toString() + price();
    }

    
}
