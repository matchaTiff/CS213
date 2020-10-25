import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private ChoiceBox<String> services;
    @FXML
    private Label choiceBoxLabel;

    public void choiceBoxButtonPushed(){
        choiceBoxLabel.setText(services.getValue().toString());
    }

    public void initialize(){
        services.getItems().add("test1");
        services.getItems().add("test2");
        services.getItems().add("test3");
    }
}
