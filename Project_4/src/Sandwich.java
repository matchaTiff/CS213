public abstract class Sandwich implements Customizable {
    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;
    protected ArrayList<Extra> extras;

    // from Customizable interface
    public boolean add(Object obj) {

    }

    public boolean remove(Object obj) {

    }

    /**
     * Abstract method that returns the price for the sandwich
     * @return the price for the sandwich (chicken, beef, fish)
     */
    public abstract double price();

    /**
     * tostring
     * @return string
     */
    @Override
    public String toString() {

    }
    
}
