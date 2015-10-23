import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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

    public void poblarVista(){
        GridPane grid_busca =(GridPane) this.lookup("#grid_busca");

        grid_busca.addRow(0,new Text("ID"));
        grid_busca.addRow(0,new Text("SIGLA"));
        grid_busca.addRow(0,new Text("SECCION"));
        grid_busca.addRow(0,new Text("HORARIO"));
        grid_busca.addRow(0,new Text("PROFESOR"));
        grid_busca.addRow(0,new Text("CUPOS"));
        grid_busca.addRow(0,new Text("CREDITOS"));

        int i=1;
        for (Ramo ramo:cont.ramos){
            grid_busca.addRow(i,new Text(String.valueOf(ramo.descriptor.id_ramo)));
            grid_busca.addRow(i,new Text(ramo.descriptor.sigla));
            grid_busca.addRow(i,new Text(String.valueOf(ramo.seccion)));
            grid_busca.addRow(i,new Text(ramo.horario));
            grid_busca.addRow(i,new Text(ramo.profesor.nombre));
            grid_busca.addRow(i,new Text(String.valueOf(ramo.cupos)));
            grid_busca.addRow(i,new Text(String.valueOf(ramo.descriptor.creditos)));
            i++;
        }

    }
}
