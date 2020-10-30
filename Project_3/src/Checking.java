/**
 * A checking account that allows deposits and withdrawals.
 * Fee is waived for an account with direct deposit.
 *
 * @author Seth Santos, Tiffany Chen
 *
 */
public class Checking extends Account {
    private boolean directDeposit;
    private static final double ANNUAL_INTEREST = 0.0005; // Interest rate in decimal form
    private static final int MONTHLY_FEE = 25;
    private static final int MIN_WAIVE_BALANCE = 1500; // Minimum balance required to waive the fee

    /**
     * Default constructor
     */
    public Checking() {
        this.directDeposit = false;
    }

    /**
     * Parameterized constructor
     * @param holder Includes the name under the account
     * @param balance Numerical balance of the account
     * @param date The date in which the account was opened
     * @param directDep Whether or not the account is direct deposit enabled
     */
    public Checking(Profile holder, double balance, Date date, boolean directDep) {
        super(holder, balance, date);
        directDeposit = directDep;
    }

    /**
     * Calculates the interest by month
     * @return The value credited to the account monthly
     */
    public double monthlyInterest(){
        // Divides interest by 12 so it equally accumulates by month
        double interestByMonth = ANNUAL_INTEREST / 12;
        return ( this.getBalance() * interestByMonth );
    }

    /**
     * Determines the monthly fee
     * @return Monthly Fee is returned
     *         Returns 0 if account is eligible for fee waiver
     */
    public double monthlyFee() {
        if( directDeposit ){ // Fee waived if direct deposit is enabled
            return 0;
        }else{
            if( this.getBalance() < MIN_WAIVE_BALANCE ){ // Determines if fee is elligible to be waived
                return MONTHLY_FEE;
            }else{ // Balance exceeds minimum to waive fee
                return 0;
            }
        }
    }

    /**
     * Formats the output of account details
     * @return Type of account with account details in String format
     */
    @Override
    public String toString() {
        if( directDeposit ){ // Determines if account belongs to loyal customer
            return ( "*Checking*" + super.toString() + "*direct deposit account*");
        }else{
            return ( "*Checking*" + super.toString() );
        }
    }

    /**
     * Getter method for direct deposit status
     * @return True or False on whether account is direct deposit set up
     */
    public boolean getIsDirectDeposit() {
        return directDeposit;
    }

    /**
     * Identifier for type of account
     * @return 1 for Checking
     */
    public int accType() {
        return 1;
    }
}
