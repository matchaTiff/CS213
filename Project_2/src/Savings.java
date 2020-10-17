/**
 * A savings account that allows deposits and withdrawals.
 * Loyal customer gets promotional interest rate 0.35%.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class Savings extends Account {
    private boolean isLoyal;
    private static final double ANNUAL_INTEREST = 0.0025; // Interest rate in decimal form
    private static final double LOYAL_ANNUAL_INTEREST = 0.0035; // Interest rate for loyal customers in decimal form
    private static final int MONTHLY_FEE = 5;
    private static final int MIN_WAIVE_BALANCE = 300; // Minimum balance required to waive the fee

    /**
     * Default constructor
     */
    public Savings() {
        super();
        isLoyal = false;
    }

    /**
     * Parameterized constructor for Account
     * @param holder Includes the name under the account
     * @param balance Numerical balance of the account
     * @param date The date in which the account was opened
     * @param loyal Whether or not the customer has loyalty status or not
     */
    public Savings(Profile holder, double balance, Date date, boolean loyal) {
        super(holder, balance, date);
        isLoyal = loyal;
    }

    /**
     * Getter method for customer loyalty status
     * @return True if customer is loyal
     *         False if customer is not loyal
     */
    public boolean getIsLoyal() {
        return isLoyal;
    }

    /**
     * Calculates the interest by month
     * @return The value credited to the account monthly
     */
    public double monthlyInterest(){
        double interestByMonth;
        if( isLoyal ){ // Divides interest by 12 so it equally accumulates by month
            interestByMonth = LOYAL_ANNUAL_INTEREST / 12;
        }else{
            interestByMonth = ANNUAL_INTEREST / 12;
        }
        return (this.getBalance() * interestByMonth);
    }

    /**
     * Determines the monthly fee
     * @return Monthly Fee is returned
     *         Returns 0 if account is eligible for fee waiver
     */
    public double monthlyFee() {
        if(this.getBalance() < MIN_WAIVE_BALANCE){ // Determines if fee is elligible to be waived
            return MONTHLY_FEE;
        }else{ // balance is <= 300
            return 0;
        }
    }

    /**
     * Formats the output of Savings account details
     * @return Type of account with Savings account details in String format
     */
    @Override
    public String toString() {
        if( isLoyal ){ // Determines if account belongs to loyal customer
            return ( "*Savings*" + super.toString() + "*special Savings account*");
        }else{
            return ( "*Savings*" + super.toString() );
        }
    }

    /**
     * Identifier for type of account
     * @return 2 for Savings
     */
    public int accType() {
        return 2;
    }
}
