import java.text.DecimalFormat;

/**
 * Array-based container class with an initial capacity of 5 and holds different account
 * instances in Checking, Savings, or MoneyMarket.
 * It will automatically grow the capacity by 5 if the database is full.
 * 
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class AccountDatabase {
    private Account[] accounts;
    private int size;

    /**
     * Constructor for AccountDatabase
     */
    public AccountDatabase() {
        accounts = new Account[5];
        size = 0;
    }

    /**
     * Searches for an account in the database
     * @param account Account that is being searched for
     * @return Returns index of the account if found. Returns -1 if not found.
     */
    private int find(Account account) {
        for(int i=0; i<size; i++){
            if( account.getHolder().equals(accounts[i].getHolder()) ){ // Compares the names of the accounts
                if(account.accType() == accounts[i].accType()) { // Compares the type of account (Checking, Savings, MM)
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Increases the size of the Account database array by 5 if max size is reached
     */
    private void grow() {
        Account[] newAccounts = new Account[accounts.length + 5]; // increase accounts by 5

        for (int i = 0; i < size; i++) { // copy items (references) from old bag to new bag
            newAccounts[i] = accounts[i];
        }
        accounts = newAccounts; // point original array to new array location
    }

    /**
     * Adds an account to the account database
     * @param account Account to be added to the database
     * @return True if account successfully added
     *         False if account already exists in database
     */
    public boolean add(Account account) {
        if( find(account) != -1 ){ //Check if account exists
            return false; // Account exists already
        }

        int lastAccount = size;
        if( accounts.length == size ){ // Increase the amount of accounts able to be held in the array
            grow();
        }
        accounts[lastAccount] = account;
        size++; // increase counter for number of accountsin the database
        return true;
    }

    /**
     * Removes an account from the account database
     * @param account Account to be removed
     * @return True if account was found and removed
     *         False if the account doesn't exist the database
     */
    public boolean remove(Account account) {
        int accountIndex = find(account);
        if( accountIndex == -1 ){ // account doesnt exist
            return false;
        }

        int lastAccount = size - 1;
        accounts[accountIndex] = accounts[lastAccount];
        accounts[lastAccount] = null;
        size--;
        return true;
    } //return false if account doesn’t exist

    /**
     * Adds money to a specific account
     * @param account Account to be deposited into
     * @param amount Amount to be deposited
     * @return True if Deposit was successful
     *         False if account does not exist in database
     */
    public boolean deposit(Account account, double amount) {
        if( find(account) == -1 ){ //Check if account exists
            return false; // Account does not exist so cannot add
        }

        account.credit(amount);
        return true;
    }

    /**
     * Takes money out of a specific account
     * @param account Account to withdraw money from
     * @param amount Amount to be withdrawn
     * @return -1: account does not exist
     *          0: Withdraw successful
     *          1: Insufficient funds
     */
    public int withdrawal(Account account, double amount) {
        if( find(account) == -1 ) { //Check if account exists
            return -1;
        }

        if( account.getBalance() >= amount ){ // Withdrawl successful
            account.debit(amount);
            return 0;
        }else{ // insufficient funds
            return 1;
        }
    }

    /**
     * Sorts database by the date the account was open in ascending order.
     * Calculate the monthly interests and fees.
     */
    private void sortByDateOpen() {
        for (int i = 0; i < size; i++) {
            
            for (int j = i + 1; j < size; j++) {
                Date iDate = accounts[i].getDate();
                Date jDate = accounts[j].getDate();
                if (iDate.compareTo(jDate) > 0){
                    Account temp = accounts[i];
                    accounts[i] = accounts[j];
                    accounts[j] = temp;
                }
            }
        }
    }

    /**
     * Sorts database by the account's last name in ascending order.
     * Calculate the monthly interests and fees.
     */
    private void sortByLastName() {
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                String iLName = accounts[i].getHolder().getLastName();
                String jLName = accounts[j].getHolder().getLastName();
                if (iLName.compareTo(jLName) > 0){
                    Account temp = accounts[i];
                    accounts[i] = accounts[j];
                    accounts[j] = temp;
                }
            }
        }
    }

    /**
     * Prints out the accounts in the database sorted by the date they were opened
     * @return a complete string of the sorted accounts
     */
    public String printByDateOpen() {
        sortByDateOpen();
        DecimalFormat df = new DecimalFormat("#0.00");
        String sortedDateString = "";
        if(size == 0) {
            return ("Database is empty.\n");
        }
        else {
            sortedDateString += ("--Printing statements by last name--\n");
            for (int i = 0; i < size; i++) {
                sortedDateString += (accounts[i].toString());
                sortedDateString += ("-interest: $" + df.format(accounts[i].monthlyInterest()) + "\n");
                accounts[i].credit(accounts[i].monthlyInterest());
                sortedDateString += ("-fee: $" + df.format(accounts[i].monthlyFee()) + "\n");
                accounts[i].debit(accounts[i].monthlyFee());
                sortedDateString += ("-new balance: $" + df.format(accounts[i].getBalance()) + "\n");
            }
            sortedDateString += ("--end of listing--\n");
        }
        return sortedDateString;
    }

    /**
     * Print out the database sorted by last name.
     * @return a complete string of the sorted accounts
     */
    public String printByLastName() {
        sortByLastName();
        DecimalFormat df = new DecimalFormat("#0.00");
        String sortedLastNameString = "";
        if(size == 0) {
            return ("Database is empty.\n");
        }
        else {
            sortedLastNameString += ("--Printing statements by last name--\n");
            for (int i = 0; i < size; i++) {
                sortedLastNameString += (accounts[i].toString());
                sortedLastNameString += ("-interest: $" + df.format(accounts[i].monthlyInterest()) + "\n");
                accounts[i].credit(accounts[i].monthlyInterest());
                sortedLastNameString += ("-fee: $" + df.format(accounts[i].monthlyFee()) + "\n");
                accounts[i].debit(accounts[i].monthlyFee());
                sortedLastNameString += ("-new balance: $" + df.format(accounts[i].getBalance()) + "\n");
            }
            sortedLastNameString += ("--end of listing--\n");
        }
        return sortedLastNameString;
    }

    /**
     * Getter method for size of account database
     * @return account database size
     */
    public int getSize() {
        return size;
    }

    /**
     * Getter method for account
     * @param index Position in the account database for the account we are getting
     * @return Account from the index
     */
    public Account getAccount(int index) {
        return accounts[index];
    }

}
