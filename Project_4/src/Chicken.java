import java.util.ArrayList;

/**
 * Chicken class that extends Sandwich class.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class Chicken extends Sandwich{

    private final double initSandwichPrice = 8.99;

    /**
     * Default constructor
     */
    public Chicken(){
        super();
        extras = new ArrayList<Extra>();
    }

    /**
     * Abstract method that returns the price for the chicken sandwich
     * @return the price for the chicken sandwich
     */
    @Override
    public double price() {
        return PER_EXTRA * extras.size() + initSandwichPrice;
    }

    @Override
    public boolean add(Object obj) {
        Extra ingredient = (Extra) obj;
        extras.add(ingredient);
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        Extra ingredient = (Extra) obj;
        extras.remove(ingredient);
        return true;
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
