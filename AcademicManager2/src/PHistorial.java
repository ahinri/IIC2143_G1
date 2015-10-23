
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ahinri on 22-10-2015.
 */
public class PHistorial extends Pane {
    HistorialAcademico ha=null;
    public PHistorial(HistorialAcademico ha) throws IOException {
        this.ha=ha;
        Parent a=FXMLLoader.load(getClass().getClassLoader().getResource("FXML/historial_academico.fxml"));
        this.getChildren().add(a);
        poblarVista(ha);

        GridPane grid_historial = (GridPane)this.lookup("#grid_historial");



    }


    public void poblarVista(HistorialAcademico ha){


    }

    public void agregarSemestre(ArrayList<Ramo> ramos){

    }

}
