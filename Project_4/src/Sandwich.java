import java.util.ArrayList;

public abstract class Sandwich implements Customizable {
    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;
    protected ArrayList<Extra> extras;

    /**
     * comment here
     * @return
     */
    @Override
    public boolean add(Object obj) {

    }

    /**
     * comment here
     * @return
     */
    @Override
    public boolean remove(Object obj) {

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
    }
    
}
