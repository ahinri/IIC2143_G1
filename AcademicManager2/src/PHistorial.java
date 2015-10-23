
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ahinri on 22-10-2015.
 */
public class PHistorial extends Pane {
    Contenido cont =Contenido.getMi_instancia();

    HistorialAcademico ha=null;

    public PHistorial(HistorialAcademico ha) throws IOException {
        this.ha=ha;
        Parent a=FXMLLoader.load(getClass().getClassLoader().getResource("FXML/historial_academico.fxml"));
        this.getChildren().add(a);
        poblarVista();
    }

    public void poblarVista(){
        Node grid_historial = this.lookup("#pane_global");
        //grid_historial.toString();
    }

    public void agregarSemestre(ArrayList<Ramo> ramos){
        int size=cont.semestres.size();
        Semestre nuevo= new Semestre(size);


    }

}
