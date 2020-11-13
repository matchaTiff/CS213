import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class Controller {

    @FXML private ComboBox sandwichChoices;
    @FXML private Label ingredients;
    @FXML private ImageView sandwichImage;

    private Image image;

    public void initialize() {
        sandwichChoices.getItems().addAll(
                "Chicken Sandwich",
                "Beef Sandwich",
                "Fish Sandwich"
                );
        sandwichChoices.getSelectionModel().selectFirst();
        if( sandwichChoices.getValue().equals("Chicken Sandwich") ) {
            ingredients.setText("Lettuce, tomato, fried chicken, mayo");
        }
    }

    public void comboBox() {
        if( sandwichChoices.getValue().equals("Chicken Sandwich") ) {
            sandwichImage.setImage(new Image("chicken_sandwich.png"));
            ingredients.setText("Lettuce, tomato, fried chicken, mayo");
        }else if( sandwichChoices.getValue().equals("Beef Sandwich") ){
            sandwichImage.setImage(new Image("beef_sandwich.png"));
            ingredients.setText("Beef, cheese, tomato, lettuce");
        }else{
            sandwichImage.setImage(new Image("fish_sandwich.png"));
            ingredients.setText("Fried fish fillet, lettuce, tartar sauce");
        }
    }
}
