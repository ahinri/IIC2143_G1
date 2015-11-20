import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fmsandoval on 08-11-2015.
 */
public class PCalificar extends Pane {

    Contenido cont = Contenido.getMi_instancia();
    Profesor profesor = null;
    Ramo ramo = null;
    ArrayList<Alumno> alumnos = null;
    ArrayList<TextField> textfields = null;

    public PCalificar(Profesor profesor, Ramo ramo) throws IOException {

        this.profesor = profesor;
        this.ramo = ramo;
        this.alumnos = new ArrayList<>();
        textfields = new ArrayList<>();
        Parent a = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/calificar.fxml"));
        this.getChildren().add(a);

        Label label_ramo = (Label)this.lookup("#label_ramo");
        label_ramo.setText(ramo.descriptor.sigla);
        Button bt_calificar = (Button)this.lookup("#bt_calificar");

        bt_calificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                calificarRamo();
                Stage stage = (Stage) bt_calificar.getScene().getWindow();
                stage.close();
            }
        });

    }

    public void calificarRamo() {
        for (Alumno alumno : alumnos) {
            int index = alumnos.indexOf(alumno);
            String nota_s = textfields.get(index).getText();
            double nota_d = Double.parseDouble(nota_s);

            for (int i = alumno.historial.semestres.size() - 1; i > -1; i--) {
                Semestre semestre = alumno.historial.semestres.get(i);
                int indexRamo = semestre.ramos.indexOf(ramo);
                if (indexRamo > -1) {
                    semestre.notas.set(indexRamo, nota_d);

                    ArrayList<Double> notas = semestre.notas;
                    String notasString = "";
                    for (int j = 0; j < notas.size(); j++) {
                        if (j == indexRamo)
                            notasString += nota_s;
                        else
                            notasString += String.valueOf(notas.get(j));
                        if (j < notas.size() - 1)
                            notasString += ";";
                    }

                    String q = "UPDATE semestres SET notas='" + notasString + "' WHERE id_semestre=" + semestre.id_semestre + ";";
                    try{cont.cargar.execute(q);}
                    catch (Exception e) {
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        System.exit(0);
                    };

                    break;
                }
            }
        }
    }


    public void poblarLista() throws IOException {
        GridPane grid_notas = (GridPane)this.lookup("#grid_notas");
        int index = 0;
        for (Alumno alumno : ramo.lista_alumnos) {
            alumnos.add(index, alumno);
            Label label_nombre = new Label();
            label_nombre.setText(alumno.nombre);
            grid_notas.add(label_nombre, 0, index);
            TextField txtf_nota = new TextField("");
            double nota = getNota(alumno);
            txtf_nota.setText(String.valueOf(nota));
            grid_notas.add(txtf_nota, 1, index);
            textfields.add(index, txtf_nota);
            index++;
        }
    }

    public double getNota(Alumno alumno) {
        for (int i = alumno.historial.semestres.size() - 1; i > -1; i--) {
            Semestre semestre = alumno.historial.semestres.get(i);
            int indexRamo = semestre.ramos.indexOf(ramo);
            if (indexRamo > -1)
                return semestre.notas.get(indexRamo);
        }
        return 0.0;
    }

}
