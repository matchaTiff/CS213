import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * Second Controller class that controls the data flow into the model object and updates the view when
 * the data changes. Controls the second stage functionality of when "Show Order" button is clicked.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class OrderController {
    @FXML private Button backButton;
    @FXML private ListView orderView;

    private Controller controller;
    private OrderController orderController = this;
    
    private Order copyOrder = new Order();

    private ArrayList<OrderLine> orderlines;

    public void getOrder(Controller controller) {
        copyOrder = controller.returnOrder();
        System.out.println(copyOrder.getOrderLines().toString());
        orderlines = copyOrder.getOrderLines();
        for(OrderLine line : orderlines) {
            orderView.getItems().add(line);
        }
    }

    public void initialize() {
    }

    public void backButtonPressed() {
        
    }
}
