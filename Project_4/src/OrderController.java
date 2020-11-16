import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    @FXML private TextField totalPrice;

    private Order copyOrder = new Order();
    //@FXML private ListView allOrders;


    private ArrayList<OrderLine> orderlines;

    public void getOrder(Controller controller) {
        copyOrder = controller.returnOrder();
        System.out.println(copyOrder.getOrderLines().toString());
        orderlines = copyOrder.getOrderLines();
        for(OrderLine line : orderlines) {
            orderView.getItems().add(line);
        }
        setPriceText();
    }

    public void clear(){
        while(copyOrder.getOrderlines().size() != 0){
            copyOrder.remove(0);
        }
        orderView.getItems().clear();
        setPriceText();
    }

    public void addSameOrderLine(){
        copyOrder.add( copyOrder.getOrderlines().get( orderView.getSelectionModel().getSelectedIndex() ).getSandwich() );
        orderView.getItems().clear();
        for(OrderLine line : orderlines) {
            orderView.getItems().add(line);
        }
        setPriceText();
    }

    public void removeOrderLine(){
        copyOrder.remove( orderView.getSelectionModel().getSelectedIndex() );
        orderView.getItems().clear();
        //orderView.getItems().addAll( copyOrder.getOrderLines() );

        for(OrderLine line : orderlines) {
            orderView.getItems().add(line);
        }
        setPriceText();
    }

    public void setPriceText(){
        double total = 0;
        for(OrderLine line : orderlines){
            total += line.getPrice();
        }
        totalPrice.setText( String.format("%.2f", total ) );
    }

    public void backButtonPressed() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Takes order details and exports it into a text file.
     * @throws FileNotFoundException
     */
    public void exportFile() throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter("order_details.txt")) {
            if(!orderlines.isEmpty()) {
                for(int i = 0; i < orderlines.size(); i++) {
                    OrderLine line = orderlines.get(i);
                    out.println(line.toString());
                }
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
