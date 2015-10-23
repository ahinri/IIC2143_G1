
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
    }

    public void poblarVista(){
        GridPane grid_historial =(GridPane) this.lookup("#grid_historial");
        grid_historial.addColumn(0,new Text("   hola"));
        grid_historial.addColumn(0,new Text("   chao"));
    }

    public void agregarSemestre(ArrayList<Ramo> ramos){
        int size=cont.semestres.size();
        Semestre nuevo= new Semestre(size);

    }

}
