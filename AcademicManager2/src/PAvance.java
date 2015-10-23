import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by ahinri on 23-10-2015.
 */
public class PAvance extends Pane {
    Contenido cont=Contenido.getMi_instancia();

    public PAvance() throws IOException {

        Parent a = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/avance.fxml"));
        this.getChildren().add(a);
    }
}
