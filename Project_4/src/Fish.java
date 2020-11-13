import java.util.ArrayList;

public class Fish extends Sandwich {

    private final double initSandwichPrice = 12.99;

    /**
     * Default constructor
     */
    public Fish(){
        super();
        extras = new ArrayList<Extra>();
    }

    /**
     * Abstract method that returns the price for the fish sandwich
     * @return the price for the fish sandwich
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
        return "Fish Sandwich; Grilled Snapper, Cilantro, Lime, Extra: " + super.toString() + price();
    }

    
}
