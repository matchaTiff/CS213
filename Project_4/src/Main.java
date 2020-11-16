import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The NewStage class sets up the second stage.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
// class NewStage {
    
//     NewStage() throws IOException {
//         Stage subStage = new Stage();
//         Parent root = FXMLLoader.load(getClass().getResource("sandwichStore2.fxml"));
//         Controller controllerInstance = new Controller();
//         FXMLLoader fxmlLoader = new FXMLLoader();
//         fxmlLoader.setController(controllerInstance);
//         subStage.setTitle("Order Details");
//         subStage.setScene(new Scene(root, 600, 400));
//         subStage.show();
//     }
// }

/**
 * The main class sets up the application by connecting the FXML file
 * and the Controller class. Launches the scene for the application.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sandwichStore.fxml"));
        Controller controllerInstance = new Controller();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(controllerInstance);
        primaryStage.setTitle("Sandwich Shop");
        primaryStage.setScene(new Scene(root, 600, 716));
        // primaryStage.getScene().getStylesheets().add("style.css");
        primaryStage.show();

    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
