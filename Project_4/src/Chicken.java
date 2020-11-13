import java.util.ArrayList;

public class Chicken extends Sandwich {
    private static final double INIT_PRICE = 8.99;

    /**
     * Default constructor
     */
    public Chicken() {
        super();
        extras = new ArrayList<Extra>();
    }

    /**
     * Abstract method that returns the price for the chicken sandwich
     * @return the price for the chicken sandwich
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
        return "Chicken Sandwich; Fried Chicken, Spicy Sauce, Pickles, Extra: " + super.toString() + price();
    }
}
