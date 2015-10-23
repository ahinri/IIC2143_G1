
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
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

    public void poblarVista() throws IOException {
        GridPane grid_historial =(GridPane) this.lookup("#grid_historial");



        int num_sem=0;

        System.out.print("NUM SEMSTRES: ");
        System.out.println(ha.semestres.size());

        for (Semestre sem:ha.semestres){

            System.out.print("NUM RAMOS: ");
            System.out.println(sem.ramos.size());

            for (Ramo ramo:sem.ramos){
                Parent ramo_gui=FXMLLoader.load(getClass().getClassLoader().getResource("FXML/ramo.fxml"));

                Text label_sigla=(Text)ramo_gui.lookup("#label_sigla");
                String sigla=ramo.descriptor.sigla;
                System.out.println(sigla);
                label_sigla.setText(sigla);
                grid_historial.addColumn(num_sem, ramo_gui);
            }
            num_sem+=1;
        }




    }

    public void agregarSemestre(ArrayList<Ramo> ramos){
        int size=cont.semestres.size();
        Semestre nuevo= new Semestre(size);

    }

}
