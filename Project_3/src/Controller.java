import java.io.PrintStream;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class Controller {
    @FXML
    private ChoiceBox<String> services;
    @FXML
    private Label choiceBoxLabel;
    @FXML
    private TextArea console;
    @FXML
    private PrintStream ps;
    @FXML
    private RadioButton checkingButton;
    @FXML
    private RadioButton savingsButton;
    @FXML
    private RadioButton moneyMarketButton;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField amount;
    @FXML
    private TextField dateField;
    @FXML
    private CheckBox optionCheckBox;
    @FXML
    private GridPane gridToHide;
    @FXML
    private MenuItem file;

    private AccountDatabase accDatabase = new AccountDatabase();

    char serviceType;
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

    @FXML
    char accType;

    public void initialize() {
        ps = new PrintStream(new Console(console));
        services.getItems().add("Open New Account");
        services.getItems().add("Close Existing Account");
        services.getItems().add("Deposit Funds");
        services.getItems().add("Withdraw Funds");
        checkingButton.setSelected(true);
        accountType();
    }

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

    public Date toDate(String date) {
        String[] dateElements = date.split("/");
        dateOpen = new Date(Integer.parseInt(dateElements[0]),
                            Integer.parseInt(dateElements[1]),
                            Integer.parseInt(dateElements[2]) );
        return dateOpen;
    }

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
                accDatabase.add(checkingAcc);
                console.appendText(checkingAcc.toString());
                break;
            case 'S':
                if(optionCheckBox.isSelected()) {
                    accOption = true;
                }
                else {
                    accOption = false;
                }
                Account savingsAcc = new Savings(holder, amountAsDouble, dateOpen, accOption);
                accDatabase.add(savingsAcc);
                console.appendText(savingsAcc.toString());
                break;
            case 'M':
                Account mmAcc = new MoneyMarket(holder, amountAsDouble, dateOpen, 0);
                accDatabase.add(mmAcc);
                console.appendText(mmAcc.toString());
                break;
            default:

        }
    }
    
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

    public void withdrawAccount() {
        Profile holder = new Profile(fName, lName);
        switch (accType) {
            case 'C':
                for (int i = 0; i < accDatabase.getSize(); i++) {
                    // check if its a checking account and holder matches
                    // 1 = checking account
                    if (accDatabase.getAccount(i).accType() == 1 
                    && holder.equals(accDatabase.getAccount(i).getHolder())) {
                        accDatabase.withdrawal(accDatabase.getAccount(i), amountAsDouble); // withdraw amount
                        console.appendText(accDatabase.getAccount(i).toString());
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
                        accDatabase.withdrawal(accDatabase.getAccount(i), amountAsDouble);
                        console.appendText(accDatabase.getAccount(i).toString());
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
                        accDatabase.withdrawal(accDatabase.getAccount(i), amountAsDouble);
                        console.appendText(accDatabase.getAccount(i).toString());
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

    public void depositAccount() {
        Profile holder = new Profile(fName, lName);
        switch (accType) {
            case 'C':
                for (int i = 0; i < accDatabase.getSize(); i++) {
                    // check if its a checking account and holder matches
                    // 1 = checking account
                    if (accDatabase.getAccount(i).accType() == 1 
                    && holder.equals(accDatabase.getAccount(i).getHolder())) {
                        accDatabase.deposit(accDatabase.getAccount(i), amountAsDouble);
                        console.appendText(accDatabase.getAccount(i).toString());
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
                        accDatabase.deposit(accDatabase.getAccount(i), amountAsDouble);
                        console.appendText(accDatabase.getAccount(i).toString());
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
                        accDatabase.deposit(accDatabase.getAccount(i), amountAsDouble);
                        console.appendText(accDatabase.getAccount(i).toString());
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

    public void importFile() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Import text file");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
    }

    public void submitButton(ActionEvent event) {
        System.setOut(ps);
        System.setErr(ps);
        importFile();
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

    public void listAccountsButton(ActionEvent event) {
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



    public void listByDateButton(ActionEvent event){
        System.setOut(ps);
        System.setErr(ps);
        console.appendText(accDatabase.printByDateOpen());
    }

    public void listByLastNameButton(ActionEvent event){
        System.setOut(ps);
        System.setErr(ps);
        console.appendText(accDatabase.printByLastName());
    }

    public class Console extends OutputStream {
        private TextArea console;

        public Console(TextArea console) {
            this.console = console;
        }

        public void appendText(String valueOf) {
            Platform.runLater(() -> console.appendText(valueOf));
        }

        public void write(int b) throws IOException {
            appendText(String.valueOf((char)b));
        }
    }
}
