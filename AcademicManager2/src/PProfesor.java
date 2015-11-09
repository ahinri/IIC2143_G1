import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by fmsandoval on 08-11-2015.
 */
public class PProfesor extends Pane {

    Contenido cont = Contenido.getMi_instancia();
    Profesor profesor = null;

    public PProfesor(Profesor profesor) throws IOException {

        this.profesor = profesor;
        Parent a = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/profesor.fxml"));
        this.getChildren().add(a);

        Button bt_calificar = (Button)this.lookup("#bt_calificar");
        poblarChoiceBox();

        bt_calificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    Ramo ramo = getRamo();
                    PCalificar pCalificar;
                    pCalificar = new PCalificar(profesor, ramo);
                    Scene scene = new Scene(pCalificar);
                    Stage stg=new Stage();
                    stg.setTitle("Academic Manager");
                    stg.setScene(scene);
                    stg.show();
                    pCalificar=(PCalificar)scene.getRoot();
                    pCalificar.poblarLista();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public void poblarChoiceBox() {
        ChoiceBox choiceBox = (ChoiceBox)this.lookup("#choice_ramos");
        for (Ramo ramo : cont.ramos) {
            if (ramo.profesor._username == profesor._username) {
                String sigla = ramo.descriptor.sigla;
                choiceBox.getItems().add(sigla);
            }
        }
        choiceBox.getSelectionModel().selectFirst();
    }

    public Ramo getRamo() {
        ChoiceBox choiceBox = (ChoiceBox)this.lookup("#choice_ramos");
        String id_ramo = choiceBox.getValue().toString();
        for (Ramo ramo : cont.ramos) {
            if (ramo.descriptor.sigla.equals(id_ramo))
                return ramo;
        }
        return null;
    }

}
