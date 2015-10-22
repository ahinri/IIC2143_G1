
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by ahinri on 22-10-2015.
 */
public class PHistorial extends Pane {

    public PHistorial(HistorialAcademico ha) throws IOException {
        Parent a=FXMLLoader.load(getClass().getClassLoader().getResource("FXML/historial_academico.fxml"));
        this.getChildren().add(a);


    }


    public void poblarVista(){

    }

}
