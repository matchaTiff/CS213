import java.io.*;
import java.text.DecimalFormat;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.application.Platform;

/**
 * Class that controls the data flow into the model object and updates the view when
 * the data changes. Handles the input commands and displays the results on the console.
 * Performs the necessary transaction based on the user's input.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class Controller {
    
    @FXML private ChoiceBox<String> services;
    @FXML private Label choiceBoxLabel;
    @FXML private TextArea console;
    @FXML private PrintStream ps;
    @FXML private RadioButton checkingButton;
    @FXML private RadioButton savingsButton;
    @FXML private RadioButton moneyMarketButton;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField amount;
    @FXML private TextField dateField;
    @FXML private CheckBox optionCheckBox;
    @FXML private GridPane gridToHide;

    private AccountDatabase accDatabase = new AccountDatabase();

    char serviceType;
    char accType;
    String fName;
    String lName;
    boolean fNameValid = false;
    boolean lNameValid = false;
    boolean amountValid = false;
    boolean dateValid = false;
    boolean accOption = false;
    double amountAsDouble;
    Date dateOpen;
    FileChooser fileChooser;

    /**
     * Initializes any controls.
    */
    public void initialize() {
        ps = new PrintStream(new Console(console));
        services.getItems().add("Open New Account");
        services.getItems().add("Close Existing Account");
        services.getItems().add("Deposit Funds");
        services.getItems().add("Withdraw Funds");
        checkingButton.setSelected(true);
        accountType();
    }

    /**
     * Checks what service the user has selected and hides the necessary fields.
    */
    public void serviceSelected() {
        switch(services.getValue()) {
            case "Open New Account":
                amount.setVisible(true);
                amount.setManaged(true);
                dateField.setVisible(true);
                gridToHide.setManaged(true);
                optionCheckBox.setVisible(true);
                optionCheckBox.setManaged(true);
                serviceType = 'O';
                break;
            case "Close Existing Account":
                amount.setVisible(false);
                amount.setManaged(false);
                dateField.setVisible(false);
                gridToHide.setManaged(false);
                optionCheckBox.setVisible(false);
                optionCheckBox.setManaged(false);
                serviceType = 'C';
                break;
            case "Deposit Funds":
                amount.setVisible(true);
                amount.setManaged(true);
                dateField.setVisible(false);
                gridToHide.setManaged(true);
                optionCheckBox.setVisible(false);
                optionCheckBox.setManaged(false);
                serviceType = 'D';
                break;
            case "Withdraw Funds":
                amount.setVisible(true);
                amount.setManaged(true);
                dateField.setVisible(false);
                gridToHide.setManaged(true);
                optionCheckBox.setVisible(false);
                optionCheckBox.setManaged(false);
                serviceType = 'W';
                break;
            default:
                console.appendText("Please select service type.\n");
                serviceType = 'F';
                break;
        }
    }

    /**
     * Checks what account type the user has selected and hides the necessary fields
     * such as the Direct Deposit or Loyal Customer checkbox.
    */
    public void accountType() {
        if(checkingButton.isSelected()) {
            accType = 'C';
            optionCheckBox.setText("Direct Deposit");
            optionCheckBox.setVisible(true);
            optionCheckBox.setManaged(true);
        }
        else if(savingsButton.isSelected()) {
            accType = 'S';
            optionCheckBox.setText("Loyal Customer");
            optionCheckBox.setVisible(true);
            optionCheckBox.setManaged(true);
        }
        else if(moneyMarketButton.isSelected()) {
            accType = 'M';
            optionCheckBox.setVisible(false);
            optionCheckBox.setManaged(false);
        }
    }

    /**
     * Error check text fields and assigns the fields valid or not valid for each service type.
     * Appends the nessecary print statements.
     * @param serviceType type of service user selected 
    */
    public void checkTextFields(char serviceType) {
        switch(serviceType) {
            case 'O':
                if(firstName.getText().equals("")) {
                    console.appendText("Please enter first name\n");
                    fNameValid = false;
                }
                else {
                    fName = firstName.getText();
                    fNameValid = true;
                }

                if(lastName.getText().equals("")) {
                    console.appendText("Please enter last name\n");
                    lNameValid = false;
                }
                else {
                    lName = lastName.getText();
                    lNameValid = true;
                }

                if (amount.getText().equals("")) {
                    console.appendText("Please enter an amount\n");
                    amountValid = false;
                }
                else {
                    if(amountIsValid(amount.getText())) {
                        amountAsDouble = Double.parseDouble(amount.getText());
                        amountValid = true;
                    }
                    else {
                        console.appendText("Invalid amount\n");
                        amountValid = false;
                    }
                }

                if (dateField.getText().equals("")) {
                    console.appendText("Please enter a date\n");
                    dateValid = false;
                }
                else {
                    if(validDate(dateField.getText())) {
                        dateOpen = toDate(dateField.getText());
                        dateValid = true;
                    }
                    else {
                        console.appendText("Invalid date\n");
                        dateValid = false;
                    }
                }
                break;

            case 'C':
                if(firstName.getText().equals("")) {
                    console.appendText("Please enter first name\n");
                    fNameValid = false;
                }
                else {
                    fName = firstName.getText();
                    fNameValid = true;
                }

                if(lastName.getText().equals("")) {
                    console.appendText("Please enter last name\n");
                    lNameValid = false;
                }
                else {
                    lName = lastName.getText();
                    lNameValid = true;
                }
                break;
            case 'D':
                if(firstName.getText().equals("")) {
                    console.appendText("Please enter first name\n");
                    fNameValid = false;
                }
                else {
                    fName = firstName.getText();
                    fNameValid = true;
                }

                if(lastName.getText().equals("")) {
                    console.appendText("Please enter last name\n");
                    lNameValid = false;
                }
                else {
                    lName = lastName.getText();
                    lNameValid = true;
                }

                if (amount.getText().equals("")) {
                    console.appendText("Please enter an amount\n");
                    amountValid = false;
                }
                else {
                    if(amountIsValid(amount.getText())) {
                        amountAsDouble = Double.parseDouble(amount.getText());
                        amountValid = true;
                    }
                    else {
                        console.appendText("Invalid amount\n");
                        amountValid = false;
                    }
                }
                break;
            case 'W':
                if(firstName.getText().equals("")) {
                    console.appendText("Please enter first name\n");
                    fNameValid = false;
                }
                else {
                    fName = firstName.getText();
                    fNameValid = true;
                }

                if(lastName.getText().equals("")) {
                    console.appendText("Please enter last name\n");
                    lNameValid = false;
                }
                else {
                    lName = lastName.getText();
                    lNameValid = true;
                }

                if (amount.getText().equals("")) {
                    console.appendText("Please enter an amount\n");
                    amountValid = false;
                }
                else {
                    if(amountIsValid(amount.getText())) {
                        amountAsDouble = Double.parseDouble(amount.getText());
                        amountValid = true;
                    }
                    else {
                        console.appendText("Invalid amount\n");
                        amountValid = false;
                    }
                }
                break;
            default:
                console.appendText("Please select service type.\n");
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
     * Check if the balance amount is valid or not.
     * 
     * @param str containing the amount as a string
     * @return true if string can be parsed as a double and not negative, false otherwise
    */
    public boolean amountIsValid(String str) {
        if(isDouble(str)) {
            amountAsDouble = Double.parseDouble(str);
            if(amountAsDouble < 0) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Changes a string date into a Date object
     * @param date the date as a string
     * @return a Date object
     */
    public Date toDate(String date) {
        String[] dateElements = date.split("/");
        dateOpen = new Date(Integer.parseInt(dateElements[0]),
                            Integer.parseInt(dateElements[1]),
                            Integer.parseInt(dateElements[2]) );
        return dateOpen;
    }

    /**
     * Determines if the Day, Month, and Year the account was opened is a valid date
     * @param date the date as a string
     * @return True if the date is valid
     *         False if the date is invalid
     */
    public boolean validDate(String date){
         try{
            String[] dateElements = date.split("/");
            dateOpen = new Date(Integer.parseInt(dateElements[0]),
                                Integer.parseInt(dateElements[1]),
                                Integer.parseInt(dateElements[2]) );
         }catch(Exception e){
             console.appendText("Please enter a valid date\n");
             return false;
         }
        if( dateOpen.isValid() ){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Open a new checking account (C)
     * Open a savings account (S)
     * Open a money market (M) account
    */
    public void createAccount(){
        Profile holder = new Profile(fName, lName);
        switch(accType){
            case 'C':
                if(optionCheckBox.isSelected()) {
                    accOption = true;
                }
                else {
                    accOption = false;
                }
                Account checkingAcc = new Checking(holder, amountAsDouble, dateOpen, accOption);
                if(accDatabase.add(checkingAcc)) {
                    console.appendText(checkingAcc.toString());
                    console.appendText("\nAccount opened and added to the database.");
                }else{
                    console.appendText("Account is already in database");
                }
                break;
            case 'S':
                if(optionCheckBox.isSelected()) {
                    accOption = true;
                }
                else {
                    accOption = false;
                }
                Account savingsAcc = new Savings(holder, amountAsDouble, dateOpen, accOption);
                if(accDatabase.add(savingsAcc)) {
                    console.appendText(savingsAcc.toString());
                    console.appendText("\nAccount opened and added to the database.");
                }else{
                    console.appendText("Account is already in database");
                }
                break;
            case 'M':
                Account mmAcc = new MoneyMarket(holder, amountAsDouble, dateOpen, 0);
                if(accDatabase.add(mmAcc)) {
                    console.appendText(mmAcc.toString());
                    console.appendText("\nAccount opened and added to the database.");
                }else{
                    console.appendText("Account is already in database");
                }
                break;
            default:

        }
    }
    
    /**
     * Close checking account (C)
     * Close savings account (S)
     * Close money market account (M)
    */
    public void closeAccount() {
        Profile holder = new Profile(fName, lName);
        switch (accType) {
            case 'C':
                for (int i = 0; i < accDatabase.getSize(); i++) {
                    // check if its a checking account and holder matches
                    // 1 = checking account
                    if (accDatabase.getAccount(i).accType() == 1
                            && holder.equals(accDatabase.getAccount(i).getHolder())) {
                        console.appendText(accDatabase.getAccount(i).toString() + " has been removed.");
                        accDatabase.remove(accDatabase.getAccount(i));
                        break;
                    }
                    if(i + 1 == accDatabase.getSize()) {
                        console.appendText("Account does not exist.");
                        break;
                    }
                }
                break;
            case 'S':
                for (int i = 0; i < accDatabase.getSize(); i++) {
                    // 2 = checking account
                    if (accDatabase.getAccount(i).accType() == 2
                            && holder.equals(accDatabase.getAccount(i).getHolder())) {
                        console.appendText(accDatabase.getAccount(i).toString() + " has been removed.");
                        accDatabase.remove(accDatabase.getAccount(i));
                        break;
                    }
                    if(i + 1 == accDatabase.getSize()) {
                        console.appendText("Account does not exist.");
                        break;
                    }
                }
                break;
            case 'M':
                for (int i = 0; i < accDatabase.getSize(); i++) {
                    // 3 = checking account
                    if (accDatabase.getAccount(i).accType() == 3
                            && holder.equals(accDatabase.getAccount(i).getHolder())) {
                        console.appendText(accDatabase.getAccount(i).toString() + " has been removed.");
                        accDatabase.remove(accDatabase.getAccount(i));
                        break;
                    }
                    if(i + 1 == accDatabase.getSize()) {
                        console.appendText("Account does not exist.");
                        break;
                    }
                }
                break;
        }
    }

    /**
     * Withdraw from checking account (C)
     * Withdraw from savings account (S)
     * Withdraw from money market account (M)
    */
    public void withdrawAccount() {
        Profile holder = new Profile(fName, lName);
        switch (accType) {
            case 'C':
                for (int i = 0; i < accDatabase.getSize(); i++) {
                    // check if its a checking account and holder matches
                    // 1 = checking account
                    if (accDatabase.getAccount(i).accType() == 1 
                    && holder.equals(accDatabase.getAccount(i).getHolder())) {
                        if(accDatabase.withdrawal(accDatabase.getAccount(i), amountAsDouble) == 0) { // withdraw amount
                            console.appendText(amountAsDouble + " withdrawn from account.\n");
                        }
                        else if(accDatabase.withdrawal(accDatabase.getAccount(i), amountAsDouble) == 1) {
                            console.appendText("Insufficient funds.\n");
                        }
                        break;
                    }
                    if(i + 1 == accDatabase.getSize()) {
                        console.appendText("Account does not exist.");
                        break;
                    }
                }
                break;
            case 'S':
                for (int i = 0; i < accDatabase.getSize(); i++) {
                    // 2 = checking account
                    if (accDatabase.getAccount(i).accType() == 2 
                    && holder.equals(accDatabase.getAccount(i).getHolder())) {
                        if(accDatabase.withdrawal(accDatabase.getAccount(i), amountAsDouble) == 0) { // withdraw amount
                            console.appendText(amountAsDouble + " withdrawn from account.\n");
                        }
                        else if(accDatabase.withdrawal(accDatabase.getAccount(i), amountAsDouble) == 1) {
                            console.appendText("Insufficient funds.\n");
                        }
                        break;
                    }
                    if(i + 1 == accDatabase.getSize()) {
                        console.appendText("Account does not exist.");
                        break;
                    }
                }
                break;
            case 'M':
                for (int i = 0; i < accDatabase.getSize(); i++) {
                    // 3 = checking account
                    if (accDatabase.getAccount(i).accType() == 3 
                    && holder.equals(accDatabase.getAccount(i).getHolder())) {
                        if(accDatabase.withdrawal(accDatabase.getAccount(i), amountAsDouble) == 0) { // withdraw amount
                            console.appendText(amountAsDouble + " withdrawn from account.\n");
                        }
                        else if(accDatabase.withdrawal(accDatabase.getAccount(i), amountAsDouble) == 1) {
                            console.appendText("Insufficient funds.\n");
                        }
                        break;
                    }
                    if(i + 1 == accDatabase.getSize()) {
                        console.appendText("Account does not exist.");
                        break;
                    }
                }
                break;
        }
    }

    /**
     * Deposit into checking account (C)
     * Deposit into savings account (S)
     * Deposit into money market account (M)
    */
    public void depositAccount() {
        Profile holder = new Profile(fName, lName);
        switch (accType) {
            case 'C':
                for (int i = 0; i < accDatabase.getSize(); i++) {
                    // check if its a checking account and holder matches
                    // 1 = checking account
                    if (accDatabase.getAccount(i).accType() == 1 
                    && holder.equals(accDatabase.getAccount(i).getHolder())) {
                        if(accDatabase.deposit(accDatabase.getAccount(i), amountAsDouble)) {
                            console.appendText(amountAsDouble + " deposited to account.");
                        }
                        break;
                    }
                    if(i + 1 == accDatabase.getSize()) {
                        console.appendText("Account does not exist.");
                        break;
                    }
                }
                break;
            case 'S':
                for (int i = 0; i < accDatabase.getSize(); i++) {
                    // 2 = savings account
                    if (accDatabase.getAccount(i).accType() == 2 
                    && holder.equals(accDatabase.getAccount(i).getHolder())) {
                        if(accDatabase.deposit(accDatabase.getAccount(i), amountAsDouble)) {
                            console.appendText(amountAsDouble + " deposited to account.");
                        }
                        break;
                    }
                    if(i + 1 == accDatabase.getSize()) {
                        console.appendText("Account does not exist.");
                        break;
                    }
                }
                break;
            case 'M':
                for (int i = 0; i < accDatabase.getSize(); i++) {
                    // 3 = savings account
                    if (accDatabase.getAccount(i).accType() == 3 
                    && holder.equals(accDatabase.getAccount(i).getHolder())) {
                        if(accDatabase.deposit(accDatabase.getAccount(i), amountAsDouble)) {
                            console.appendText(amountAsDouble + " deposited to account.");
                        }
                        break;
                    }
                    if(i + 1 == accDatabase.getSize()) {
                        console.appendText("Account does not exist.");
                        break;
                    }
                }
                break;
        }
    }

    /**
     * Checks to see if input line in a file is valid.
     * 
     * @param line array of tokens from the file line
    */
    public int checkInput(String[] line) {
        if(line[0].equals("C") || line[0].equals("S") || line[0].equals("M") ) { // checking account
            if(line.length == 6) { // check if input line is the right size
                return 1;
            }
        }
        return -1;
    }

    /**
     * Takes in a text file and imports the data into the database.
     * Data tokens in the text files delimited by the commas (,).
     * @throws Exception
     */
    public void importFile() throws FileNotFoundException {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Import text file");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        BufferedReader br = new BufferedReader( new FileReader(selectedFile) );

        String st;
        try {
            while((st = br.readLine()) != null) {
                if(checkInput(st.split(",")) == 1) {
                    String[] accParts = st.split(",");
                    Profile profile = new Profile(accParts[1], accParts[2]);
                    amountAsDouble = Double.parseDouble(accParts[3]);
                    String[] dateParts = accParts[4].split("/");
                    dateOpen = new Date(Integer.parseInt(dateParts[0]),
                                        Integer.parseInt(dateParts[1]),
                                        Integer.parseInt(dateParts[2])
                                        );
                    switch(accParts[0]){
                        case "C":
                            if(accParts[5].equals("true")){
                                Account checkingAcc = new Checking(profile, amountAsDouble, dateOpen, true);
                                accDatabase.add(checkingAcc);
                            }else{
                                Account checkingAcc = new Checking(profile, amountAsDouble, dateOpen, false);
                                accDatabase.add(checkingAcc);
                            }
                            break;
                        case "S":
                            if(accParts[5].equals("true")){
                                Account savingsAcc = new Savings(profile, amountAsDouble, dateOpen, true);
                                accDatabase.add(savingsAcc);
                            }else{
                                Account savingsAcc = new Savings(profile, amountAsDouble, dateOpen, false);
                                accDatabase.add(savingsAcc);
                            }
                            break;
                        case "M":
                            int withdrawals = Integer.parseInt(accParts[5]);
                            Account moneyMarket = new MoneyMarket(profile, amountAsDouble, dateOpen, withdrawals);
                            accDatabase.add(moneyMarket);
                            break;
                    }
                }
            }
        }
        catch (IOException e) {
            console.appendText("An error has occurred.\n");
        }
        console.appendText("Imported File.\n");
    }

    /**
     * Takes account database and exports it into a text file.
     * @throws FileNotFoundException
     */
    public void exportFile() throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter("Database.txt")) {
            if(accDatabase.getSize() == 0) {
                console.appendText("Database is empty.\n");
            }
            else {
                DecimalFormat df = new DecimalFormat("#0.00");
                for(int i = 0; i < accDatabase.getSize(); i++) {
                    Account currentAcc = accDatabase.getAccount(i);
                    switch(currentAcc.accType()) {
                        case 1: // checking account type
                            Checking currentAccChecking = (Checking) currentAcc;
                            if(currentAccChecking.getIsDirectDeposit()) {
                                out.println("C" + "," + currentAcc.getHolder().getFirstName() + "," 
                                + currentAcc.getHolder().getLastName() + "," + df.format(currentAcc.getBalance()) 
                                + "," + currentAcc.getDate().toString() + ","
                                + "true");
                            }
                            else {
                                out.println("C" + "," + currentAcc.getHolder().getFirstName() + "," 
                                + currentAcc.getHolder().getLastName() + "," + df.format(currentAcc.getBalance()) 
                                + "," + currentAcc.getDate().toString() + ","
                                + "false");
                            }
                            break;
                        case 2:
                            Savings currentAccSavings = (Savings) currentAcc;
                            if(currentAccSavings.getIsLoyal()) {
                                out.println("S" + "," + currentAcc.getHolder().getFirstName() + "," 
                                + currentAcc.getHolder().getLastName() + "," + df.format(currentAcc.getBalance()) 
                                + "," + currentAcc.getDate().toString() + ","
                                + "true");
                            }
                            else {
                                out.println("S" + "," + currentAcc.getHolder().getFirstName() + "," 
                                + currentAcc.getHolder().getLastName() + "," + df.format(currentAcc.getBalance()) 
                                + "," + currentAcc.getDate().toString() + ","
                                + "false");
                            }
                            break;
                        case 3:
                            MoneyMarket currentAccMM = (MoneyMarket) currentAcc;
                            out.println("M" + "," + currentAcc.getHolder().getFirstName() + "," 
                                + currentAcc.getHolder().getLastName() + "," + df.format(currentAcc.getBalance()) 
                                + "," + currentAcc.getDate().toString() + ","
                                + currentAccMM.getWithdrawals());
                            break;
                    }
                }
                console.appendText("Database has been exported.\n");
            }
            out.close();
        } catch (FileNotFoundException e) {
            console.appendText("File doesn't exist\n");
            e.printStackTrace();
        }
    }

    /**
     * Performs coresponding service types when the submit button is pressed.
     */
    public void submitButton() {
        System.setOut(ps);
        System.setErr(ps);
        if(services.getValue() == null) {
            console.appendText("Please enter service type.\n");
        }
        else {
            serviceSelected();
            checkTextFields(serviceType);
            switch(serviceType){
                case 'O':
                    if(fNameValid == true && lNameValid == true && amountValid == true && dateValid == true) {
                        createAccount();
                    }
                    else {
                        console.appendText("ERROR! Invalid info.\n");
                    }
                    break;
                case 'C':
                    if(fNameValid == true && lNameValid == true) {
                        if(accDatabase.getSize() == 0) {
                            console.appendText("Account does not exist.");
                        }
                        else {
                            closeAccount();
                        }
                    }
                    break;
                case 'W':
                    if(fNameValid == true && lNameValid == true && amountValid == true) {
                        if(accDatabase.getSize() == 0) {
                            console.appendText("Account does not exist.");
                        }
                        else {
                            withdrawAccount();
                        }
                    }
                    break;
                case 'D':
                    if(fNameValid == true && lNameValid == true && amountValid == true) {
                        if(accDatabase.getSize() == 0) {
                            console.appendText("Account does not exist.");
                        }
                        else {
                            depositAccount();
                        }
                    }
                    break;
            }

        }
        console.appendText("\n");

    }

    /**
     * Print the list of accounts in the database
    */
    public void listAccountsButton() {
        System.setOut(ps);
        System.setErr(ps);
        if (accDatabase.getSize() == 0) {
            console.appendText("Database is empty." + "\n");
        } else {
            console.appendText("--Listing accounts in the database--" + "\n");
            for (int i = 0; i < accDatabase.getSize(); i++) {
                console.appendText(accDatabase.getAccount(i).toString() + "\n");
            }
            console.appendText("--end of listing--" + "\n");
        }
    }

    /**
     * Calculate the monthly interests and fees, and print the account statements, 
     * sorted by the dates opened in ascending order
     */
    public void listByDateButton(){
        System.setOut(ps);
        System.setErr(ps);
        console.appendText(accDatabase.printByDateOpen());
    }

    /**
     * Calculate the monthly interests and fees, and print the account statements, 
     * sorted by last name opened in ascending order
     */
    public void listByLastNameButton(){
        System.setOut(ps);
        System.setErr(ps);
        console.appendText(accDatabase.printByLastName());
    }

    /**
     * Represents a console viewable through a TextArea.
     */
    public class Console extends OutputStream {
        private TextArea console;

        /**
         * Creates a console context.
         * @param console
         *      The text area to output the consoles text.
         */
        public Console(TextArea console) {
            this.console = console;
        }

        /**
         * Appends text data to the text area.
         * @param valueOf the string to be appended to the text area
         */
        public void appendText(String valueOf) {
            Platform.runLater(() -> console.appendText(valueOf));
        }

        @Override
        public void write(int b) throws IOException {
            appendText(String.valueOf((char)b));
        }
    }
}
