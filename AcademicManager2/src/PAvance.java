import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ahinri on 23-10-2015.
 */
public class PAvance extends Pane {
    Contenido cont=Contenido.getMi_instancia();

    HistorialAcademico ha = null;

    public PAvance(HistorialAcademico ha) throws IOException {

        this.ha = ha;
        Parent a = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/avance.fxml"));
        this.getChildren().add(a);
    }

    public void poblarVista() throws IOException {
        GridPane grid_avance =(GridPane) this.lookup("#grid_avance");
        grid_avance.getChildren().clear();

        ArrayList<String> cursosAprobados = new ArrayList<String>();
        ArrayList<String> cursosFaltantes = new ArrayList<String>();

        for (Semestre sem : ha.semestres) {
            for (int index = 0; index < sem.ramos.size(); index++) {
                if (sem.notas.get(index) >= 4) {
                    cursosAprobados.add(sem.ramos.get(index).descriptor.sigla);
                }
            }
        }

        for (Semestre sem : ha.semestres) {
            for (int index = 0; index < sem.ramos.size(); index++) {
                if (sem.notas.get(index) < 4) {
                    String sigla = sem.ramos.get(index).descriptor.sigla;
                    if (!cursosAprobados.contains(sigla)) {
                        if (!cursosFaltantes.contains(sigla)) {
                            cursosFaltantes.add(sigla);
                        }
                    }
                }
            }
        }

        for (Ramo ramoMalla : ha.malla.ramos) {
            String sigla = ramoMalla.descriptor.sigla;
            if (!cursosAprobados.contains(sigla)) {
                if (!cursosFaltantes.contains(sigla)) {
                    cursosFaltantes.add(sigla);
                }
            }
        }

        Parent header1_gui = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/ramo.fxml"));
        Text label1_sigla = (Text)header1_gui.lookup("#label_sigla");
        header1_gui.setStyle("-fx-background-color: blue");
        label1_sigla.setText("Aprobados");
        grid_avance.add(header1_gui, 0, 0);
        Parent header2_gui = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/ramo.fxml"));
        Text label2_sigla = (Text)header2_gui.lookup("#label_sigla");
        header2_gui.setStyle("-fx-background-color: red");
        label2_sigla.setText("Faltantes");
        grid_avance.add(header2_gui, 1, 0);

        int index = 1;
        for (String ramoAprobado : cursosAprobados) {
            Parent ramo_gui = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/ramo.fxml"));
            Text label_sigla = (Text)ramo_gui.lookup("#label_sigla");
            String sigla = ramoAprobado;
            ramo_gui.setStyle("-fx-background-color: blue");
            label_sigla.setText(sigla);
            grid_avance.add(ramo_gui, 0, index);
            index++;
        }

        index = 1;
        for (String ramoFaltante : cursosFaltantes) {
            Parent ramo_gui = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/ramo.fxml"));
            Text label_sigla = (Text)ramo_gui.lookup("#label_sigla");
            String sigla = ramoFaltante;
            ramo_gui.setStyle("-fx-background-color: red");
            label_sigla.setText(sigla);
            grid_avance.add(ramo_gui, 1, index);
            index++;
        }

    }
}
