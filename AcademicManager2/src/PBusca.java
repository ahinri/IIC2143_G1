import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by ahinri on 23-10-2015.
 */
public class PBusca extends Pane {
    Contenido cont=Contenido.getMi_instancia();

    public PBusca() throws IOException {

        Parent a = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/buscacursos.fxml"));
        this.getChildren().add(a);
    }
}
