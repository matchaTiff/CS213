import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * User interface class that handles the input commands and output.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class Shopping {

    DecimalFormat df = new DecimalFormat("#0.00"); // format to two decimal places

    /**
     * Checks what command the user has inputted and performs the appropriate commands.
     * 
     * @param command the user has inputted
     * @param sBag is the shopping bag they're trying to print, checkout, add, or remove to
     * @param item the user is trying to add/remove if any
    */
    public void checkCommand(char command, ShoppingBag sBag, GroceryItem item) {
        if (command == 'A') {
            sBag.add(item);
        }
        else if (command == 'R') {
            sBag.remove(item);
        }
        else if (command == 'P') {
            if (sBag.getSize() == 0) { // bag size is 0
                System.out.println("The bag is empty!");
            }
            else if (sBag.getSize() == 1){ // bag size is 1
                System.out.println("**You have " + sBag.getSize() + " item in the bag.");
                sBag.print();
                System.out.println("**End of list");
            }
            else {
                System.out.println("**You have " + sBag.getSize() + " items in the bag.");
                sBag.print();
                System.out.println("**End of list");
            }
        }
        else if (command == 'C') {
            if (sBag.getSize() == 0) {
                System.out.println("Unable to check out, the bag is empty!");
            }
            else {
                if (sBag.getSize() == 1) {
                System.out.println("**Checking out " + sBag.getSize() + " item: ");
                }
                else {
                    System.out.println("**Checking out " + sBag.getSize() + " items: ");
                }
                sBag.print();
                double finalPrice = sBag.salesPrice() + sBag.salesTax();
                System.out.println("*Sales total: $" + sBag.salesPrice() + "\n" 
                + "*Sales tax: $" + df.format(sBag.salesTax()) + "\n" 
                + "*Total amount paid: $" + df.format(finalPrice));
                sBag.checkout();
            }
        }
        else {
            System.out.println("Invalid command!");
        }
    }

    /**
     * Checks if the input line is valid.
     * 
     * @param scannedLine is the command line that was scanned
     * @return 1 if theres only a command inputted, 
     * 2 if it's a command, name, price, taxable, 
     * and 0 if invalid
    */
    public int checkString(String[] scannedLine) {
        if (scannedLine.length == 1 && scannedLine[0].length() == 1) { // if theres only one element and a single letter, its a command
            return 1;
        }
        else if (scannedLine.length == 4 
        && scannedLine[0].length() == 1 
        && (scannedLine[3].length() == 5 || scannedLine[3].length() == 4)) { // check if it has command, name, price, taxable
            return 2;
        }
        return 0;
    }

    /**
     * Runs the project by scanning the user input and perform the appropriate command
     * and print message.
    */
    public void run(){
        ShoppingBag sBag = new ShoppingBag();
        GroceryItem item = new GroceryItem("", 0, false);

        Scanner groceryItem = new Scanner(System.in);  // Create a Scanner object

        char command;

        while (groceryItem.hasNextLine()) {
            
            String line = groceryItem.nextLine();
            if (line.equals("Q")) { // quit program if command is Q
                System.out.println("Thanks for shopping with us!");
                break;
            }
            String[] splitted = line.split("\\s+"); // split string by whitespace
            
            int checker = checkString(splitted);
            if (checker == 1) {
                command = splitted[0].charAt(0); // get command
                checkCommand(command, sBag, item);
            }
            else if (checker == 2) {
                command = splitted[0].charAt(0); // get command from input
                Double price = Double.parseDouble(splitted[2]); // get price from input
                boolean taxable = Boolean.valueOf(splitted[3]); // get taxable from input
                item = new GroceryItem(splitted[1], price, taxable);
                checkCommand(command, sBag, item);
            }
            else {
                System.out.println("Invalid command!");
            }
        }
        groceryItem.close();
    }
}
