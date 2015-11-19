import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
public class PEditarRamo extends Pane {

    Contenido cont = Contenido.getMi_instancia();
    Admin admin = null;
    int id_antiguo;
    int semestre_antiguo;
    int seccion_antiguo;
    int anio_antiguo;

    public PEditarRamo(Admin admin) throws IOException {

        this.admin = admin;
        id_antiguo = -1;
        semestre_antiguo = -1;
        seccion_antiguo = -1;
        anio_antiguo = -1;
        Parent a = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/editar_ramo.fxml"));
        this.getChildren().add(a);

        Button bt_editar = (Button)this.lookup("#bt_editar");
        ComboBox combo_ramos = (ComboBox)this.lookup("#combo_ramos");

        poblarRamos();
        poblarProf();
        poblarDesc();


        bt_editar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                editarRamo();
                Stage stage = (Stage) bt_editar.getScene().getWindow();
                stage.close();
            }
        });

        combo_ramos.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String sigla = newValue.toString();
                updateValues(sigla);
            }
        });

    }

    public void updateValues(String sigla) {
        for (Ramo ramo : cont.ramos) {
            if (ramo.descriptor.sigla.equals(sigla)) {
                id_antiguo = ramo.descriptor.id_ramo;
                semestre_antiguo = ramo.num_semestre;
                seccion_antiguo = ramo.seccion;
                anio_antiguo = ramo.anio;
                TextField txtf = (TextField)this.lookup("#txt_horario");
                txtf.setText(ramo.horario);
                txtf = (TextField)this.lookup("#txt_sala");
                txtf.setText(ramo.sala);
                txtf = (TextField)this.lookup("#txt_seccion");
                txtf.setText(String.valueOf(ramo.seccion));
                txtf = (TextField)this.lookup("#txt_cupos");
                txtf.setText(String.valueOf(ramo.cupos));
                txtf = (TextField)this.lookup("#txt_anio");
                txtf.setText(String.valueOf(ramo.anio));
                txtf = (TextField)this.lookup("#txt_semestre");
                txtf.setText(String.valueOf(ramo.num_semestre));
                ComboBox combo = (ComboBox)this.lookup("#combo_prof");
                combo.setValue(ramo.profesor.nombre);
                combo = (ComboBox)this.lookup("#combo_desc");
                combo.setValue(ramo.descriptor.sigla);
                break;
            }
        }
    }

    public void editarRamo() {
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
        combo = (ComboBox)this.lookup("#combo_ramos");
        Ramo ramo = getRamo(combo.getValue().toString());

        String q = "UPDATE ramos SET horario='" + horario + "', id=" + descriptor.id_ramo + ", sala='" + sala + "', secion=" + seccion + ", cupos=" + cupos + ", año=" + anio + ", semestre=" + semestre + ", id_profesor=" + profesor.id_usuario + " WHERE id=" + id_antiguo + " AND semestre=" + semestre_antiguo + " AND secion=" + seccion_antiguo + " AND año=" + anio_antiguo + ";" ;
        try{cont.cargar.execute(q);}
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        };

        admin.editarRamo(ramo, horario, sala, seccion, cupos, anio, semestre, profesor, descriptor);
    }

    public void poblarRamos() {
        ComboBox combo = (ComboBox)this.lookup("#combo_ramos");
        for (Ramo ramo : cont.ramos) {
            combo.getItems().add(ramo.descriptor.sigla);
        }
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

    public Ramo getRamo(String sigla) {
        for (Ramo ramo :cont.ramos) {
            if (ramo.descriptor.sigla.equals((sigla))) {
                return ramo;
            }
        }
        return null;
    }

}
