import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by fmsandoval on 23-10-2015.
 */
public class PAdmin extends Pane {

    Contenido cont = Contenido.getMi_instancia();
    Admin admin = null;

    public PAdmin(Admin admin) throws IOException {

        this.admin = admin;
        Parent a = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/admin.fxml"));
        this.getChildren().add(a);

        Button bt_crear_malla = (Button)this.lookup("#bt_crear_malla");
        Button bt_crear_ramo = (Button)this.lookup("#bt_crear_ramo");
        Button bt_edit_malla = (Button)this.lookup("#bt_edit_malla");
        Button bt_edit_ramo = (Button)this.lookup("#bt_edit_ramo");
        Button bt_crear_desc = (Button)this.lookup("#bt_crear_desc");

        bt_crear_ramo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    PCrearRamo pCrearRamo;
                    pCrearRamo = new PCrearRamo(admin);
                    Scene scene = new Scene(pCrearRamo);
                    Stage stg=new Stage();
                    stg.setTitle("Academic Manager");
                    stg.setScene(scene);
                    stg.show();
                    pCrearRamo=(PCrearRamo)scene.getRoot();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        bt_edit_ramo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    PEditarRamo pEditarRamo;
                    pEditarRamo = new PEditarRamo(admin);
                    Scene scene = new Scene(pEditarRamo);
                    Stage stg=new Stage();
                    stg.setTitle("Academic Manager");
                    stg.setScene(scene);
                    stg.show();
                    pEditarRamo=(PEditarRamo)scene.getRoot();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        bt_crear_malla.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    PCrearMalla pCrearMalla;
                    pCrearMalla = new PCrearMalla(admin);
                    Scene scene = new Scene(pCrearMalla);
                    Stage stg=new Stage();
                    stg.setTitle("Academic Manager");
                    stg.setScene(scene);
                    stg.show();
                    pCrearMalla=(PCrearMalla)scene.getRoot();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        bt_crear_desc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    PCrearDesc pCrearDesc;
                    pCrearDesc = new PCrearDesc(admin);
                    Scene scene = new Scene(pCrearDesc);
                    Stage stg=new Stage();
                    stg.setTitle("Academic Manager");
                    stg.setScene(scene);
                    stg.show();
                    pCrearDesc=(PCrearDesc)scene.getRoot();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

}
