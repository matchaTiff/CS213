/**
 * Extra class that defines a extra ingredient.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class Extra {
    private String name;

    /**
     * Default Constructor
     */
    public Extra(String name) {
        this.name = name;
    }

    /**
     * Getter method for extra ingredient name
     * @return extra ingredient name
     */
    public String getExtraName() {
        return name;
    }

    /**
     * @return String of extra name
     */
    @Override
    public String toString() {
        return name;
    }
}