import java.text.DecimalFormat;
/**
 * This is the class for all the functional methods used in the program that correspond to commands
 * This is the container class that defines the abstract data type for a shopping bag and its operations. A
 * shopping bag holds an array of grocery items.
 * @author Seth Santos, Tiffany Chen
 */

public class ShoppingBag{

    public static void main(String[] args) {
        // test add()
        GroceryItem item = new GroceryItem("meat", 3.99, false);
        ShoppingBag input = new ShoppingBag();
        input.add(item);

        ShoppingBag expected = new ShoppingBag();
        expected.bag = new GroceryItem[1];
        expected.bag[0] = item;

        if (expected.bag[0] == input.bag[0]) {
            System.out.print("add() method" + "\n" + "Expected output: Meat added to the bag.");
        }
        else {
            System.out.println("Incorrect output: item was not added to the bag.");
        }

        // test remove()
        input.remove(item);
        expected.bag[0] = null;

        if (expected.bag[0] == input.bag[0]) {
            System.out.println("\n" + "remove() method" + "\n" + "Expected output: Meat 3.99 removed.");
        }
        else {
            System.out.println("Incorrect output: Unable to remove, this item is not in the bag.");
        }

        // test grow()
        System.out.println("\n" + "grow() method");
        input.add(new GroceryItem("meat", 3.99, false));
        input.add(new GroceryItem("milk", 1.99, false));
        input.add(new GroceryItem("eggs", 2.99, false));
        input.add(new GroceryItem("cheese", 4.99, false));
        input.add(new GroceryItem("chocolate", 5.99, true));
        input.add(new GroceryItem("carrot", 1.99, false));

        System.out.println("\n" + "Expected output should say 6 items in the bag: ");
        System.out.println("**You have " + input.size + " items in the bag.");
        input.print();

        // test salesTax()
        System.out.println("\n" + "salesTax() method");
        System.out.println("Expected output: " + "\n" 
        + "**Checking out 6 items: " + "\n" 
        + "meat: $3.99 : tax free" + "\n" 
        + "milk: $1.99 : tax free" + "\n" 
        + "eggs: $2.99 : tax free" + "\n" 
        + "cheese: $4.99 : tax free" + "\n" 
        + "chocolate: $5.99 : is taxable" + "\n" 
        + "carrot: $1.99 : tax free" + "\n" 
        + "*Sales total: $21.94" + "\n"
        + "*Sales tax: $0.40" + "\n" 
        + "*Total amount paid: $22.34");

        System.out.println("\n" + "Our output: ");
        System.out.println("**Checking out " + input.size + " items: ");
        input.print();

        DecimalFormat df = new DecimalFormat("#0.00"); // format to two decimal places
        double finalPrice = input.salesPrice() + input.salesTax();
        System.out.println("*Sales total: $" + input.salesPrice() + "\n" 
        + "*Sales tax: $" + df.format(input.salesTax()) + "\n" 
        + "*Total amount paid: $" + df.format(finalPrice));
        input.checkout();
    }

    private GroceryItem[] bag; // array-based implementation of the bag
    private int size; // number of items currently in the bag
    private int capacity; //current capacity

    private double SALES_TAX_RATE = 6.625;

    /**
     * Constructor for the ShoppingBag object
     */
    public ShoppingBag() {
        this.bag = null;
        this.size = 0;
        this.capacity = 0;
    }

    /**
     * @return size of shopping bag
     */
    public int getSize() {
        return size;
    }

    /**
     * Clear everything in shopping bag if the user inputs checkout
     */
    public void checkout() {
        for (int i = 0; i < size; i++){
            bag[i] = null;
        }
        size = 0;
    }

    /**
     * The bag array is iterated through until the item is found.
     * @param item object that is being searched for in the bag array
     * @return index of the shopping bag in the bag array
     */
    private int find(GroceryItem item) { // helper method to find an item
        for (int i=0; i<size; i++){
            if (item.equals(bag[i]) ){
                return i; //item was found and index is returned
            }
        }
        return -1; // Could not find item
    }
    
    /**
     * Grows the capacity of the shopping bag by 5 if full.
     */
    private void grow() {
        GroceryItem[] newBag = new GroceryItem[capacity + 5]; // increase bag by 5
        capacity += 5;

        for (int i = 0; i < size; i++) { // copy items (references) from old bag to new bag
            newBag[i] = bag[i];
        }
        bag = newBag; // point original array to new array location
    }

    /**
     * An item passed as a parameter is added to the end of the array.
     * If the array is full prior to the addition, the grow() method is invoked.
     * @param item object that is being added to the bag array
     */
    public void add(GroceryItem item) {
        if (size == capacity){
            grow(); // increase the capacity of the bag to fit more items
        }
        bag[size] = item; // puts the item in the last position of the array
        size++; //increases the number of items currently in the bag

        String itemName = item.getName();
        System.out.println( itemName + " added to the bag.");
    }

    /**
     * An item is passed as a parameter intended to be removed from the bag array.
     * If the item is found, the position in the array the item is in is made null
     * and the last item in the array is moved in its place.
     * @param item object to be removed.
     * @return true or false depending if the removal was successful or unsuccessful respectively.
     */
    public boolean remove(GroceryItem item) {
        int lastItemIndex = size-1;
        int itemIndex = find(item);

        if (itemIndex != -1) { // item exists in the bag
            if (itemIndex == lastItemIndex) { // Checks if the item is the last in the array
                bag[itemIndex] = null; // made null
            } else { // replaces item to be removed with the last item
                bag[itemIndex] = bag[lastItemIndex];
                bag[lastItemIndex] = null;
            }
            size--; // decreases size of the bag
            System.out.println( item.getName() + " " + item.getPrice() + " removed.");
            return true;
        }else { // item does not exist in the bag
            System.out.println("Unable to remove, this item is not in the bag.");
        }
        return false;
    }

    /**
     * Calculates the price of all the items before tax in the bag.
     * @return the total price.
     */
    public double salesPrice() {
        double total = 0;
        for (int i = 0; i < size; i++){ // adds the sales price of the item to the total
            total += bag[i].getPrice();
        }
        return total;
    }

    /**
     * Calculates only the total amount in taxes.
     * @return only the total amount in taxes.
     */
    public double salesTax() { // Gets the total Sales Tax on every item in the bag combined.
        double taxTotal = 0;
        for (int i = 0; i < size; i++){ // adds the tax of each item if it is taxable
            if (bag[i].getTaxable()){ // checks if the item is taxable or not
                double taxAmount = (SALES_TAX_RATE / 100) * bag[i].getPrice();
                taxTotal += taxAmount;
            }
        }
        return taxTotal;
    }

    /**
     * Prints out the names of all the items in the bag.
     */
    public void print() {
        if (bag != null) {
            for (int i = 0; i < size; i++) {
                System.out.println( bag[i].toString() );
            }
        }
    }
}