import java.io.PrintStream;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
                serviceType = 'O';
                break;
            case "Close Existing Account":
                amount.setVisible(false);
                amount.setManaged(false);
                dateField.setVisible(false);
                gridToHide.setManaged(false);
                serviceType = 'C';
                break;
            case "Deposit Funds":
                amount.setVisible(true);
                amount.setManaged(true);
                dateField.setVisible(false);
                gridToHide.setManaged(true);
                serviceType = 'D';
                break;
            case "Withdraw Funds":
                amount.setVisible(true);
                amount.setManaged(true);
                dateField.setVisible(false);
                gridToHide.setManaged(true);
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
                break;
            case 'M':
                Account mmAcc = new MoneyMarket(holder, amountAsDouble, dateOpen, 0);
                accDatabase.add(mmAcc);
                break;
            default:

        }
    }
    
    public void closeAccount() {
        
    }

    public void submitButton(ActionEvent event) {
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
                    closeAccount();
                    break;
            }

        }
        console.appendText("\n");

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
