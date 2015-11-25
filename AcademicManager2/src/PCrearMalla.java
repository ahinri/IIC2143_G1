import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by fmsandoval on 24-11-2015.
 */
public class PCrearMalla extends Pane {

    Contenido cont = Contenido.getMi_instancia();
    Admin admin = null;

    public PCrearMalla(Admin admin) throws IOException {

        this.admin = admin;
        Parent a = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/crear_malla.fxml"));
        this.getChildren().add(a);

        Button bt_crear = (Button)this.lookup("#bt_crear");
        Button bt_ramos_add = (Button)this.lookup("#bt_ramos_add");
        Button bt_ramos_remove = (Button)this.lookup("#bt_ramos_remove");

        poblarRamos();

        bt_crear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                crearMalla();
                Stage stage = (Stage) bt_crear.getScene().getWindow();
                stage.close();
            }
        });

        bt_ramos_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                agregarRamo();
            }
        });

        bt_ramos_remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                quitarRamo();
            }
        });

    }

    public void crearMalla() {
        TextField txtf = (TextField)this.lookup("#txtf_creditos");
        int max_creditos_semestre = Integer.parseInt(txtf.getText());
        txtf = (TextField)this.lookup("#txtf_carrera");
        String carrera = txtf.getText();
        txtf = (TextField)this.lookup("#txtf_reprobados");
        int max_ramos_reprobados = Integer.parseInt(txtf.getText());
        Text text = (Text)this.lookup("#text_ramos");
        String[] siglas = text.getText().split("\n");
        String siglas_linea = "";
        for (String sigla : siglas) {
            if (!siglas_linea.equals(""))
                siglas_linea += ";";
            int id = getIdRamo(sigla);
            siglas_linea += id;
        }

        String q = "INSERT INTO mallas VALUES (" + max_creditos_semestre + ",'" + carrera + "'," + max_ramos_reprobados + "," + cont.mallas.size() + ",'" + siglas_linea + "');" ;
        try{cont.cargar.execute(q);}
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        };

        admin.crearMalla(max_creditos_semestre, carrera, max_ramos_reprobados, cont.mallas.size(), siglas);
    }

    public void poblarRamos() {
        ComboBox combo = (ComboBox)this.lookup("#combo_ramos_add");
        for (DescriptorRamo desc : cont.descriptores) {
            combo.getItems().add(desc.sigla);
        }
    }

    public void agregarRamo() {
        ComboBox combo_add = (ComboBox)this.lookup("#combo_ramos_add");
        ComboBox combo_remove = (ComboBox)this.lookup("#combo_ramos_remove");
        Object value = combo_add.getValue();
        String sigla = value.toString();
        Text text = (Text)this.lookup("#text_ramos");
        if (!text.getText().equals(""))
            text.setText(text.getText() + "\n");
        text.setText(text.getText() + sigla);
        combo_add.getItems().remove(value);
        combo_remove.getItems().add(value);
        combo_add.getSelectionModel().clearSelection();
    }

    public void quitarRamo() {
        ComboBox combo_remove = (ComboBox)this.lookup("#combo_ramos_remove");
        ComboBox combo_add = (ComboBox)this.lookup("#combo_ramos_add");
        Object value = combo_remove.getValue();
        String sigla = value.toString();
        Text text = (Text)this.lookup("#text_ramos");
        String nuevoTexto = "";
        String[] lineas = text.getText().split("\n");
        for (String linea : lineas) {
            if (!linea.equals(sigla)) {
                if (!nuevoTexto.equals(""))
                    nuevoTexto += "\n";
                nuevoTexto += linea;
            }
        }
        text.setText(nuevoTexto);
        combo_remove.getItems().remove(value);
        combo_add.getItems().add(value);
        combo_remove.getSelectionModel().clearSelection();
    }

    public int getIdRamo(String sigla) {
        for (DescriptorRamo descriptor : cont.descriptores) {
            if (descriptor.sigla.equals(sigla))
                return descriptor.id_ramo;
        }
        return 0;
    }

}
