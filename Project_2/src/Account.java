import java.text.DecimalFormat;

/**
 * Abstract class that defines the common features of all account types.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public abstract class Account {
    private Profile holder;
    private double balance;
    private Date dateOpen;

    /**
     * Default Constructor for Account
     */
    public Account() {
        holder = new Profile();
        balance = 0;
        dateOpen = new Date();
    }

    /**
     * Parameterized constructor for Account
     * @param holder Includes the name under the account
     * @param balance Numerical balance of the account
     * @param dateOpen The date in which the account was opened
     */
    public Account(Profile holder, double balance, Date dateOpen) {
        this.holder = holder;
        this.balance = balance;
        this.dateOpen = dateOpen;
    }

    /**
     * Removing money from an account
     * @param amount Value to be removed
     */
    public void debit(double amount) {
        balance -= amount;
    } //decrease the balance by amount

    /**
     * Adding money to an account
     * @param amount Value to be added
     */
    public void credit(double amount) {
        balance += amount;
    } //increase the balance by amount

    /**
     * Getter method for balance
     * @return balance of account
     */
    public double getBalance(){
        return balance;
    }

    /**
     * Formats the output of account details
     * @return Information about the account: Name + balance + date account was opened
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return holder + "*$" + df.format(balance) + "*" + dateOpen;
    }

    /**
     * Abstract method that calculates the interest by month for an account.
     * @return The value credited to the account monthly
     */
    public abstract double monthlyInterest();

    /**
     * Abstract method that calculates the fees by month for an account.
     * @return Monthly Fee is returned
     *         Returns 0 if account is eligible for fee waiver
     */
    public abstract double monthlyFee();

    /**
     * Getter method for account holder
     * @return The Profile of the account holder (first and last name)
     */
    public Profile getHolder() {
        return holder;
    }

    /**
     * Abstract method that gets the according account type.
     * @return an int where Checking = 1, Savings = 2, Money Market = 3
     */
    public abstract int accType();

    /**
     * Method that gets the date the account opened.
     * @return date opened
     */
    public Date getDate() {
        return dateOpen;
    }

}
