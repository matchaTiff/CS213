/**
 * A money market account that allows deposits and withdrawals.
 * Fee cannot be waived if the number of withdrawals per statement exceeds 6.
 *
 * @author Seth Santos, Tiffany Chen
 *
 */
public class MoneyMarket extends Account{
    private int withdrawals;
    private static final double ANNUAL_INTEREST = 0.0065; // Interest rate in decimal form
    private static final int MONTHLY_FEE = 12;
    private static final int MIN_WAIVE_BALANCE = 2500; // Minimum balance required to waive the fee

    /**
     * Default constructor for MoneyMarket
     */
    public MoneyMarket() {
        withdrawals = 0;
    }

    /**
     * Parameterized constructor for MoneyMarket
     * @param holder Includes the name under the account
     * @param balance Numerical balance of the account
     * @param date The date in which the account was opened
     * @param withdrawAmt The amount of times money has been withdrawn from the account
     */
    public MoneyMarket(Profile holder, double balance, Date date, int withdrawAmt) {
        super(holder, balance, date);
        withdrawals = withdrawAmt;
    }

    /**
     * Removes a certain amount from the account balance with incrementing number of withdrawals
     * @param amount Value to be removed from balance
     */
    @Override
    public void debit (double amount) {
        super.debit(amount);
        withdrawals++;
    }

    /**
     * Calculates the interest earned per month based on current account balance
     * @return interest in dollars the balance has accumulated in the month
     */
    @Override
    public double monthlyInterest(){
        // Divides interest by 12 so it equally accumulates by month
        double interestByMonth = ANNUAL_INTEREST / 12;
        return ( this.getBalance() * interestByMonth );
    }

    /**
     * Determines the monthly fee
     * @return Monthly Fee is returned
     *          Returns 0 if account is eligible for fee waiver
     */
    @Override
    public double monthlyFee() {
        if( withdrawals <= 6 ){
            if( this.getBalance() < MIN_WAIVE_BALANCE ){ // balance below minimum required to waive fee
                return MONTHLY_FEE;
            }else{ // Min balance met and withdrawals does not exceed 6
                return 0;
            }
        }else{ // withdrawals exceed 6
            return MONTHLY_FEE;
        }
    }

    /**
     * Formats the output of account details
     * @return Type of account with account details in String format
     */
    @Override
    public String toString() {
        if (withdrawals == 1) {
            return ("*MoneyMarket*" + super.toString() + "*" + withdrawals + " withdrawal*");
        }
        else {
            return ("*MoneyMarket*" + super.toString() + "*" + withdrawals + " withdrawals*");
        }
    }

    /**
     * Getter method for withdrawals
     * @return number of withdrawals enacted on this account
     */
    public int getWithdrawals() {
        return withdrawals;
    }

    /**
     * Identifier for type of account
     * @return 3 for MoneyMarket
     */
    public int accType() {
        return 3;
    }
}
