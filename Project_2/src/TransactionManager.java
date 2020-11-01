import java.util.Scanner;

/**
 * User interface class that handles the input commands and displays the results on the console.
 * Each transaction begins with a two-letter command identifying the transaction type and 
 * account type, followed by the data tokens.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class TransactionManager {
    private AccountDatabase accDatabase;

    /**
     * Constructor for AccountDatabase
     */
    public TransactionManager() {
        accDatabase = new AccountDatabase();
    }

    /**
     * Checks what command the user has inputted and performs the appropriate commands.
     * 
     * @param line array of tokens from the command line the user has inputted
    */
    public void checkInput(String[] line) {
        if(line[0].equals("OC") || line[0].equals("OS")) { // O commands
            if(line.length < 6) { // check if input line is the right size
                System.out.println("Command '" + line[0] + "' not supported!");
            }
            else {
                openAccount(line);
            }
        }
        else if(line[0].equals("OM")) {
            if(line.length < 5) {
                System.out.println("Command '" + line[0] + "' not supported!");
            }
            else {
                openAccount(line);
            }
        }
        else if(line[0].equals("CC") || line[0].equals("CS") || line[0].equals("CM")) { // C commands
            if(line.length < 3) {
                System.out.println("Command '" + line[0] + "' not supported!");
            }
            else if(accDatabase.getSize() == 0) {
                System.out.println("Account does not exist.");
            }
            else {
                closeAccount(line);
            }
        }
        else if(line[0].equals("DC") || line[0].equals("DS") || line[0].equals("DM")) { // D commands
            if(line.length < 4) {
                System.out.println("Command '" + line[0] + "' not supported!");
            }
            else if(accDatabase.getSize() == 0) {
                System.out.println("Account does not exist.");
            }
            else {
                depositAccount(line);
            }
        }
        else if(line[0].equals("WC") || line[0].equals("WS") || line[0].equals("WM")) { // W commands
            if(line.length < 4) {
                System.out.println("Command '" + line[0] + "' not supported!");
            }
            else if(accDatabase.getSize() == 0) {
                System.out.println("Account does not exist.");
            }
            else {
                withdrawAccount(line);
            }
        }
        else if(line[0].equals("PA") || line[0].equals("PD") || line[0].equals("PN")) { // P commands
            if(line.length == 1) {
                printAccount(line);
            }
            else {
                System.out.println("Command '" + line[0] + "' not supported!");
            }
        }
        else {
            System.out.println("Command '" + line[0] + "' not supported!");
        }
    }

    /**
     * Performs the O commands such as
     * Open a new checking account (OC)
     * Open a savings account (OS)
     * Open a money market (OM) account
     * 
     * @param line array of tokens from the command line the user has inputted
    */
    public void openAccount(String[] line) {
        String[] date = line[4].split("/");
        Date dateOpen = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        if(dateOpen.isValid() && isDouble(line[3])) {
            Profile accountHolder = new Profile(line[1],line[2]);
            double balance = Double.parseDouble(line[3]);
            boolean accOption;
            String accOptionString;

            switch (line[0]) {
                case "OC":
                    accOptionString = line[5];
                    if(accOptionString.equalsIgnoreCase("true")) {
                        accOption = true;
                    }
                    else if(accOptionString.equalsIgnoreCase("false")) {
                        accOption = false;
                    }
                    else {
                        System.out.println("Input data type mismatch.");
                        return;
                    }
                    Account checkingAcc = new Checking(accountHolder, balance, dateOpen, accOption);
                    accDatabase.add(checkingAcc);
                    break;
                case "OS":
                    accOptionString = line[5];
                    if(accOptionString.equalsIgnoreCase("true")) {
                        accOption = true;
                    }
                    else if(accOptionString.equalsIgnoreCase("false")) {
                        accOption = false;
                    }
                    else {
                        System.out.println("Input data type mismatch.");
                        return;
                    }
                    Account savingsAcc = new Savings(accountHolder, balance, dateOpen, accOption);
                    accDatabase.add(savingsAcc);
                    break;
                case "OM":
                    Account mmAcc = new MoneyMarket(accountHolder, balance, dateOpen, 0);
                    accDatabase.add(mmAcc);
                    break;
                default:
                    System.out.println("Command '" + line[0] + "' not supported!");
            }
        }
        else {
            if(isDouble(line[3]) == true) {
                System.out.println(line[4] + " is not a valid date!");
            }
            else {
                System.out.println("Input data type mismatch.");
            }
        }
    }

    /**
     * Performs the C commands such as closing an account with the associated name.
     * Close checking account (CC)
     * Close savings account (CS)
     * Close money market account (CM)
     * 
     * @param line array of tokens from the command line the user has inputted
    */
    public void closeAccount(String[] line) {
        Date dateOpen = new Date(1, 1, 2020);
        Profile accountHolder = new Profile(line[1],line[2]);

        switch (line[0]) {
            case "CC":
                Account checkingAcc = new Checking(accountHolder, 0, dateOpen, false);
                accDatabase.remove(checkingAcc);
                break;
            case "CS":
                Account savingsAcc = new Savings(accountHolder, 0, dateOpen, false);
                accDatabase.remove(savingsAcc);
                break;
            case "CM":
                Account mmAcc = new MoneyMarket(accountHolder, 0, dateOpen, 0);
                accDatabase.remove(mmAcc);
                break;
            default:
                System.out.println("Command '" + line[0] + "' not supported!");
        }
    }

    /**
     * Performs the D commands, deposit funds to an existing account with the associated name.
     * Deposit into checking account (DC)
     * Deposit into savings account (DS)
     * Deposit into money market account (DM)
     * 
     * @param line array of tokens from the command line the user has inputted
    */
    public void depositAccount(String[] line) {
        Profile accountHolder = new Profile(line[1],line[2]);

        if(isDouble(line[3])) { // check if balance is valid
            double balance = Double.parseDouble(line[3]);
            switch (line[0]) {
                case "DC":
                    for (int i = 0; i < accDatabase.getSize(); i++) {
                        // check if its a checking account and holder matches
                        // 1 = checking account
                        if (accDatabase.getAccount(i).accType() == 1 
                        && accountHolder.equals(accDatabase.getAccount(i).getHolder())) {
                            accDatabase.deposit(accDatabase.getAccount(i), balance);
                            break;
                        }
                        if(i + 1 == accDatabase.getSize()) {
                            System.out.println("Account does not exist.");
                            break;
                        }
                    }
                    break;
                case "DS":
                    for (int i = 0; i < accDatabase.getSize(); i++) {
                        // 2 = savings account
                        if (accDatabase.getAccount(i).accType() == 2 
                        && accountHolder.equals(accDatabase.getAccount(i).getHolder())) {
                            accDatabase.deposit(accDatabase.getAccount(i), balance);
                            break;
                        }
                        if(i + 1 == accDatabase.getSize()) {
                            System.out.println("Account does not exist.");
                            break;
                        }
                    }
                    break;
                case "DM":
                    for (int i = 0; i < accDatabase.getSize(); i++) {
                        // 3 = savings account
                        if (accDatabase.getAccount(i).accType() == 3 
                        && accountHolder.equals(accDatabase.getAccount(i).getHolder())) {
                            accDatabase.deposit(accDatabase.getAccount(i), balance);
                            break;
                        }
                        if(i + 1 == accDatabase.getSize()) {
                            System.out.println("Account does not exist.");
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Command '" + line[0] + "' not supported!");
            }
        }
        else {
            System.out.println("Input data type mismatch.");
        }
    }

    /**
     * Performs the W commands, withdraw funds from an existing account with the associated name.
     * Withdraw from checking account (WC)
     * Withdraw from savings account (WS)
     * Withdraw from money market account (WM)
     * 
     * @param line array of tokens from the command line the user has inputted
    */
    public void withdrawAccount(String[] line) {
        Profile accountHolder = new Profile(line[1],line[2]);

        if(isDouble(line[3])) {
            double balance = Double.parseDouble(line[3]);
            switch (line[0]) {
                case "WC":
                    for (int i = 0; i < accDatabase.getSize(); i++) {
                        // check if its a checking account and holder matches
                        // 1 = checking account
                        if (accDatabase.getAccount(i).accType() == 1 
                        && accountHolder.equals(accDatabase.getAccount(i).getHolder())) {
                            accDatabase.withdrawal(accDatabase.getAccount(i), balance); // withdraw amount
                            break;
                        }
                        if(i + 1 == accDatabase.getSize()) {
                            System.out.println("Account does not exist.");
                            break;
                        }
                    }
                    break;
                case "WS":
                    for (int i = 0; i < accDatabase.getSize(); i++) {
                        // 2 = checking account
                        if (accDatabase.getAccount(i).accType() == 2 
                        && accountHolder.equals(accDatabase.getAccount(i).getHolder())) {
                            accDatabase.withdrawal(accDatabase.getAccount(i), balance);
                            break;
                        }
                        if(i + 1 == accDatabase.getSize()) {
                            System.out.println("Account does not exist.");
                            break;
                        }
                    }
                    break;
                case "WM":
                    for (int i = 0; i < accDatabase.getSize(); i++) {
                        // 3 = checking account
                        if (accDatabase.getAccount(i).accType() == 3 
                        && accountHolder.equals(accDatabase.getAccount(i).getHolder())) {
                            accDatabase.withdrawal(accDatabase.getAccount(i), balance);
                            break;
                        }
                        if(i + 1 == accDatabase.getSize()) {
                            System.out.println("Account does not exist.");
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Command '" + line[0] + "' not supported!");
            }
        }
        else {
            System.out.println("Input data type mismatch.");
        }
    }

    /**
     * Performs the P commands, print the list of accounts.
     * (PA) Print the list of accounts in the database
     * 
     * (PD) Calculate the monthly interests and fees, and print the account statements, 
     * sorted by the dates opened in ascending order
     * 
     * (PN) Calculate the monthly interests and fees, and print the account statements, 
     * sorted by last name opened in ascending order
     * 
     * @param line array of tokens from the command line the user has inputted
    */
    public void printAccount(String[] line) {
        switch (line[0]) {
            case "PA":
                accDatabase.printAccounts();
                break;
            case "PD":
                accDatabase.printByDateOpen();
                break;
            case "PN":
                accDatabase.printByLastName();
                break;
            default:
                System.out.println("Command '" + line[0] + "' not supported!");
        }
    }

    /**
     * Check if the string can be parsed as a double.
     * 
     * @param str containing the value
     * @return true if string can be parsed as a double, false otherwise
    */
    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Runs the project by scanning the user input and perform the appropriate command
     * and print message.
    */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitted = line.split("\\s+"); // split string by whitespace
            if(splitted[0].equals("Q")) {
                System.out.println("Transaction processing completed.");
                break;
            }
            checkInput(splitted);
        }
        scanner.close();
    }
}
