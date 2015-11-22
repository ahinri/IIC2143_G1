import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by fmsandoval on 23-10-2015.
 */
public class PCrearRamo extends Pane {

    Contenido cont = Contenido.getMi_instancia();
    Admin admin = null;

    public PCrearRamo(Admin admin) throws IOException {

        this.admin = admin;
        Parent a = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/crear_ramo.fxml"));
        this.getChildren().add(a);

        Button bt_crear = (Button)this.lookup("#bt_crear");

        poblarProf();
        poblarDesc();


        bt_crear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                crearRamo();
                Stage stage = (Stage) bt_crear.getScene().getWindow();
                stage.close();
            }
        });

    }

    public void crearRamo() {
        TextField txtf = (TextField)this.lookup("#txt_horario");
        String horario = txtf.getText();
        txtf = (TextField)this.lookup("#txt_sala");
        String sala = txtf.getText();
        txtf = (TextField)this.lookup("#txt_seccion");
        int seccion = Integer.parseInt(txtf.getText());
        txtf = (TextField)this.lookup("#txt_cupos");
        int cupos = Integer.parseInt(txtf.getText());
        txtf = (TextField)this.lookup("#txt_anio");
        int anio = Integer.parseInt(txtf.getText());
        txtf = (TextField)this.lookup("#txt_semestre");
        int semestre = Integer.parseInt(txtf.getText());
        ComboBox combo = (ComboBox)this.lookup("#combo_prof");
        Profesor profesor = getProfesor(combo.getValue().toString());
        combo = (ComboBox)this.lookup("#combo_desc");
        DescriptorRamo descriptor = getDescriptor(combo.getValue().toString());

        String q = "INSERT INTO ramos VALUES (" + descriptor.id_ramo + ",'" + horario + "','" + sala + "'," + seccion + "," + cupos + "," + anio + "," + semestre + "," + profesor.id_usuario + ", ';' ," + cont.ramos.size()+1 + ");" ;
        try{cont.cargar.execute(q);}
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        };

        admin.crearRamo(cont.ramos.size()+1,horario,sala,seccion,cupos,anio,semestre,profesor,descriptor);
    }

    public void poblarProf() {
        ComboBox combo = (ComboBox)this.lookup("#combo_prof");
        for (Profesor prof : cont.profesores) {
            combo.getItems().add(prof.nombre);
        }
    }

    public void poblarDesc() {
        ComboBox combo = (ComboBox)this.lookup("#combo_desc");
        for (DescriptorRamo desc : cont.descriptores) {
            combo.getItems().add(desc.sigla);
        }
    }

    public Profesor getProfesor(String nombre) {
        for (Profesor prof :cont.profesores) {
            if (prof.nombre.equals((nombre))) {
                return prof;
            }
        }
        return null;
    }

    public DescriptorRamo getDescriptor(String sigla) {
        for (DescriptorRamo desc : cont.descriptores) {
            if (desc.sigla.equals(sigla)) {
                return desc;
            }
        }
        return null;
    }

}
