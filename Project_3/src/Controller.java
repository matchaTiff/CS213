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
            optionCheckBox.setText("Direct Deposit");
            optionCheckBox.setVisible(true);
            optionCheckBox.setManaged(true);
        }
        else if(savingsButton.isSelected()) {
            optionCheckBox.setText("Loyal Customer");
            optionCheckBox.setVisible(true);
            optionCheckBox.setManaged(true);
        }
        else if(moneyMarketButton.isSelected()) {
            optionCheckBox.setVisible(false);
            optionCheckBox.setManaged(false);
        }
    }

    public void checkTextFields(char choice) {
        switch(choice) {
            case 'O':
                if(firstName.getText().equals("") || lastName.getText().equals("") || amount.getText().equals("")) {
                    System.out.println("Please enter your account information.");
                }
                else {
                    // check if amount is valid
                }
                break;
            case 'C':
                if(firstName.getText().equals("") || lastName.getText().equals("")) {
                    System.out.println("Please enter your account information.");
                }
                else {
                    // idk
                }
                break;
            case 'D':
                if(firstName.getText().equals("") || lastName.getText().equals("") || amount.getText().equals("")) {
                    System.out.println("Please enter your account information.");
                }
                else {
                    // check if amount is valid
                }
                break;
            case 'W':
                if(firstName.getText().equals("") || lastName.getText().equals("") || amount.getText().equals("")) {
                    System.out.println("Please enter your account information.");
                }
                else {
                    // check if amount is valid
                }
                break;
            default:
                // code block
        }
        if(firstName.getText().equals("")) {
            System.out.println("blank");
        }
        else {
            System.out.println(firstName.getText());
        }
        if(lastName.getText().equals("")) {
            System.out.println("blank");
        }
        else {
            System.out.println(lastName.getText());
        }
        if(amount.getText().equals("")) {
            System.out.println("blank");
        }
        else {
            System.out.println(amount.getText());
        }
        if(dateField.getText().equals("")) {
            System.out.println("blank");
        }
        else {
            System.out.println(dateField.getText());
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
        }

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
