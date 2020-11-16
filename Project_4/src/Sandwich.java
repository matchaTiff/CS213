import java.util.ArrayList;
import java.util.Arrays;

/**
 * Abstract class that describes a sandwich. Be able to add extra ingredients to
 * the sandwich as well as remove them.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public abstract class Sandwich implements Customizable {
    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;
    protected ArrayList<Extra> extras;

    /**
     * Add the extra ingredient to the array "extras"
     * @param obj the object that to be added
     * @return true if added, false if otherwise
     */
    @Override
    public boolean add(Object obj) {
        if( extras.size() < MAX_EXTRAS ) {
            Extra ingredient = (Extra) obj;
            extras.add(ingredient);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Remove the extra ingredient
     * @param obj the object that to be removed
     * @return true if removed, false if otherwise
     */
    @Override
    public boolean remove(Object obj) {
        Extra ingredient = (Extra) obj;
        extras.remove(ingredient);
        return true;
    }

    /**
     * Abstract method that returns the price for the sandwich
     * @return the price for the sandwich (chicken, beef, fish)
     */
    public abstract double price();

    /**
     * Outputs the extra ingredients of the sandwich if any
     * @return string of extra ingredients
     */
    @Override
    public String toString() {
        // print out extra ingredients
        return Arrays.toString(extras.toArray());
    }
    
}
