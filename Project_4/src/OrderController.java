import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Second Controller class that controls the data flow into the model object and updates the view when
 * the data changes. Controls the second stage functionality of when "Show Order" button is clicked.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class OrderController {
    @FXML private Button backButton;
    
    private Order copyOrder;

    public void getOrder(Order order) {
        copyOrder = order;
    }

    public void initialize() {

    }

    public void backButtonPressed() {
        
    }

}
