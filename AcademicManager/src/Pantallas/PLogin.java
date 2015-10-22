package Pantallas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by ahinri on 22-10-2015.
 */
public class PLogin extends Pane {

    public PLogin() throws IOException {
        Parent a=FXMLLoader.load(getClass().getClassLoader().getResource("FXML/login.fxml"));
        this.getChildren().add(a);
    }

}
