import Pantallas.PLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application{


	public static void main(String[] args) throws IOException {
        launch(args);

	}


    @Override
    public void start(Stage primaryStage) throws Exception {

        PLogin plogin=new PLogin();
        Scene scene = new Scene(plogin);

        primaryStage.setTitle("Academic Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
