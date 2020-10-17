import java.text.DecimalFormat;

/**
 * This class defines the abstract data type GroceryItem.
 * Encapsulates the data fields and methods of a grocery item.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */

public class GroceryItem {
    private String name;
    private double price;
    private boolean taxable;

    /**
     * Class constructor that initializes the data fields of a grocery item.
     * 
     * @param name name of the grocery item
     * @param price price of the grocery item
     * @param taxable if the grocery item is taxable or not
     */
    public GroceryItem(String name, double price, boolean taxable) {
        this.name = name;
        this.price = price;
        this.taxable = taxable;
    }

    /**
     * @return name of grocery item
     */
    public String getName() {
        return name;
    }

    /**
     * @return price of grocery item
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return true or false of grocery item if it is taxable
     */
    public boolean getTaxable() {
        return taxable;
    }

    /**
     * Determines if all data field values of two objects are the same.
     * In this case, this compares two GroceryItem objects.
     * 
     * @param obj GroceryItem object
     * @return true if this object is the same as the obj argument; false otherwise
     * 
    */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GroceryItem) {
            GroceryItem item = (GroceryItem) obj;
            if (this.name.equals(item.name) && 
            this.price == item.price &&
            this.taxable == item.taxable) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns a string representation of an object.
     * Format: itemName: $xx.xx : tax free
     * 
     * @return a string representation of an object
    */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        String taxStatus;
        if (taxable) { // checks whether the item is taxable and sets the string "taxStatus"
            taxStatus = "is taxable";
        }
        else {
            taxStatus = "tax free";
        }
        return name + ": $" + df.format(price) + " : " + taxStatus;
    }
}
