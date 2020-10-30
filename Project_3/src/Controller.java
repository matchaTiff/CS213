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
    // ChoiceBox services = new ChoiceBox ();
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

    char choice;
    boolean amountValid = false;
    double amountAsDouble;
    String dateAsString;
    Date dateOpen;
    char accType;

    public void initialize() {
        ps = new PrintStream(new Console(console));
        services.getItems().add("Open new account");
        services.getItems().add("Close existing account");
        services.getItems().add("Deposit funds");
        services.getItems().add("Withdraw funds");
        checkingButton.setSelected(true);
        checkRadioButton();
    }

    public void choicePushed() {
        switch(services.getValue()) {
            case "Open new account":
                amount.setVisible(true);
                amount.setManaged(true);
                dateField.setVisible(true);
                gridToHide.setManaged(true);
                choice = 'O';
                break;
            case "Close existing account":
                amount.setVisible(false);
                amount.setManaged(false);
                dateField.setVisible(false);
                gridToHide.setManaged(false);
                choice = 'C';
                break;
            case "Deposit funds":
                amount.setVisible(true);
                amount.setManaged(true);
                dateField.setVisible(false);
                gridToHide.setManaged(true);
                choice = 'D';
                break;
            case "Withdraw funds":
                amount.setVisible(true);
                amount.setManaged(true);
                dateField.setVisible(false);
                gridToHide.setManaged(true);
                choice = 'W';
                break;
            default:
                System.out.println("Please select service type.");
                choice = 'F';
                break;
        }
    }

    public void checkRadioButton() {
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

    public void checkTextFields(char choice) {

        try {
            amountAsDouble = Double.parseDouble(amount.getText());
        }catch(Exception e){}
        switch(choice) {
            case 'O':
                if(firstName.getText().equals("") || lastName.getText().equals("") || amount.getText().equals("")) {
                    console.appendText("Please enter your account information O.");
                }
                else {
                    // check if amount is valid
                    if(amountAsDouble < 0){
                        amountValid = false;
                    }else{
                        amountValid = true;
                    }
                }
                break;
            case 'C':
                if(firstName.getText().equals("") || lastName.getText().equals("")) {
                    console.appendText("Please enter your account information C.");
                }
                else {
                    // idk
                }
                break;
            case 'D':
                if(firstName.getText().equals("") || lastName.getText().equals("") || amount.getText().equals("")) {
                    console.appendText("Please enter your account information D.");
                }
                else {
                    // check if amount is valid
                    if(amountAsDouble < 0){
                        amountValid = false;
                    }else{
                        amountValid = true;
                    }
                }
                break;
            case 'W':
                if(firstName.getText().equals("") || lastName.getText().equals("") || amount.getText().equals("")) {
                    console.appendText("Please enter your account information W.");
                }
                else {
                    // check if amount is valid
                    if(amountAsDouble < 0){
                        amountValid = false;
                    }else{
                        amountValid = true;
                    }
                }
                break;
            default:
                // code block
        }
        console.appendText("\n");

        if(firstName.getText().equals("")) {
            console.appendText("Please enter a first name\n");
        }
        else {
            console.appendText(firstName.getText() + " ");
        }
        if(lastName.getText().equals("")) {
            console.appendText("Please enter a last name\n");
        }
        else {
            console.appendText(lastName.getText() + " ");
        }

        if(choice != 'C') {
            if (amount.getText().equals("")) {
                console.appendText("Please enter an amount\n");
            } else {
                console.appendText(amount.getText() + " ");
            }

            if(choice == 'O') {
                if (dateField.getText().equals("")) {
                    console.appendText("Please enter a date\n");
                } else {
                    console.appendText(dateField.getText());
                    dateAsString = dateField.getText();
                }
            }
        }

    }

    public boolean validDate(String date){
         try{
            String[] dateElements = date.split("/");
            dateOpen = new Date(Integer.parseInt(dateElements[0]),
                                Integer.parseInt(dateElements[1]),
                                Integer.parseInt(dateElements[2]) );
         }catch(Exception e){
             console.appendText("\nPlease enter a valid date");
             return false;
         }
        if( dateOpen.isValid() ){
            return true;
        }else{
            return false;
        }
    }

    public boolean validInfo(String fName, String lName, String date, boolean amtValid){
        if(fName.equals("") || lName.equals("")){
            return false;
        }

        if(!amtValid){
            return false;
        }

        if(dateAsString != null){
            if( validDate(dateAsString) ){
                return true;
            }else{
                console.appendText("\nDate is invalid");
                return false;
            }
        }else{
            return false;
        }

    }

    public void createAccount(){
        Profile holder = new Profile(firstName.getText(), lastName.getText());
        switch(accType){
            case 'C':
                Account checkingAcc = new Checking();
                break;
            case 'S':
                Account savingsAcc = new Savings();
                break;
            case 'M':
                Account moneyMarketAcc = new MoneyMarket();
                break;
            default:

        }
    }

    public void submitButton(ActionEvent event) {
        System.setOut(ps);
        System.setErr(ps);
        if(services.getValue() == null) {
            System.out.println("Please enter service type.");
        }
        else {
            choicePushed();
            checkTextFields(choice);
            if( validInfo(firstName.getText(), lastName.getText(), dateAsString, amountValid) ){
                console.appendText("\nEverything is valid!");
                switch(choice){
                    case 'O':
                        createAccount();
                        break;
                }

            }else{
                console.appendText("\nERROR! Invalid info.\n");
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
