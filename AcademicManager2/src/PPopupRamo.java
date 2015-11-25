import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class PPopupRamo extends Pane {
    Contenido cont = Contenido.getMi_instancia();

    Ramo ramo = null;
    Alumno a_actual=null;
    TextArea ta_comentarios = null;

    public PPopupRamo(Ramo ramo, Alumno a_actual) throws IOException {
        this.ramo = ramo;
        this.a_actual=a_actual;
        Parent a = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/ramo_popup.fxml"));
        System.out.print("FXML: ");
        System.out.println(this.getChildren());
        this.getChildren().add(a);




    }

    public void poblarVista(){
        Label programa=(Label)this.lookup("#label_desc");
        programa.setText(ramo.descriptor.programa);
        programa.setWrapText(true);

        VBox vbox_comentarios=(VBox)this.lookup("#vbox_comentarios");
        ArrayList<String[]> comentarios_ramo = null;
        comentarios_ramo = obtenerComentarios(ramo.nrc);
        for (String[] comentario:comentarios_ramo){
            HBox hbox_comentario = new HBox(5);
            hbox_comentario.getChildren().addAll(
                    new Label(comentario[1]),
                    new Label(comentario[0])
            );
            hbox_comentario.getChildren().get(0).setStyle("-fx-font-weight: bold;");
            vbox_comentarios.getChildren().add(hbox_comentario);
        }

        Button bt_comentarios = (Button)this.lookup("#bt_comentarios");
        ta_comentarios=(TextArea) this.lookup("#ta_comentarios");

        bt_comentarios.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                guardarComentario(ramo.nrc,ta_comentarios.getText(),a_actual._username);
                HBox hbox_comentario = new HBox(5);
                hbox_comentario.getChildren().addAll(
                        new Label(a_actual._username),
                        new Label(ta_comentarios.getText())
                );
                hbox_comentario.getChildren().get(0).setStyle("-fx-font-weight: bold;");
                vbox_comentarios.getChildren().add(hbox_comentario);
            }

        });

    }



    public ArrayList<String[]> obtenerComentarios(int nrc) {
        ArrayList<String[]> answ = new ArrayList<>();
        for (String[] datax : cont.comentarios) {
            if (datax[0].equals(Integer.toString(nrc))) {
                answ.add(new String[]{datax[1], datax[2]});
            }
        }
        return answ;
    }

    public void guardarComentario(int nrc, String comentario, String usuario) {

        cont.comentarios.add(new String[]{Integer.toString(nrc), comentario, usuario});
        String query_foro = "INSERT INTO foro VALUES (" + nrc + ", '" + comentario + "'" + ",'" + usuario + "');";
        try {
            cont.cargar.execute(query_foro);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

    }
}
