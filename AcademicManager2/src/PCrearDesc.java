import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fmsandoval on 19-11-2015.
 */
public class PCrearDesc extends Pane {

    Contenido cont = Contenido.getMi_instancia();
    Admin admin = null;

    public PCrearDesc(Admin admin) throws IOException {

        this.admin = admin;
        Parent a = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/crear_desc.fxml"));
        this.getChildren().add(a);

        Button bt_crear = (Button)this.lookup("#bt_crear");

        bt_crear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                crearDesc();
                Stage stage = (Stage) bt_crear.getScene().getWindow();
                stage.close();
            }
        });

    }

    public void crearDesc() {
        TextField txtf = (TextField)this.lookup("#txt_creditos");
        int creditos = Integer.parseInt(txtf.getText());
        txtf = (TextField)this.lookup("#txt_sigla");
        String sigla = txtf.getText();
        txtf = (TextField)this.lookup("#txt_programa");
        String programa = txtf.getText();

        int id = cont.descriptores.size() + 1;
        String q = "INSERT INTO descriptores VALUES ('" + sigla + "', " + creditos + ", '" + programa + "', " + id + ");";
        try{cont.cargar.execute(q);}
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        };

        admin.crearDesc(sigla, creditos, programa, id);
    }

}
