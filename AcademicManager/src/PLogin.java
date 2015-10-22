import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PLogin extends Pane {

    public PLogin() throws IOException {
        Parent a=FXMLLoader.load(getClass().getClassLoader().getResource("FXML/login.fxml"));
        this.getChildren().add(a);

        Button bt_ingresar = (Button)this.lookup("#bt_ingresar");
        TextField tf_user = (TextField)this.lookup("#tf_user");
        TextField tf_pass = (TextField)this.lookup("#tf_pass");


        bt_ingresar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //si el login es exitoso, abrir el historial academico
                if(login(tf_user.getText(),tf_pass.getText())){
                    try {
                        PHistorial pHistorial=new PHistorial(get_historial(tf_user.getText()));
                        Scene scene = new Scene(pHistorial);
                        Stage stg=new Stage();
                        stg.setTitle("Academic Manager");
                        stg.setScene(scene);
                        stg.show();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }

            }
        });

    }

    public boolean login (String user,String pass){
        for (Usuario usuarioEnLista : cont.usuarios) {
        	if (usuarioEnLista.username == user) {
        		if (usuarioEnLista.password == pass) {
        			return true;
        		}
        	}
        }
        return false;
    }

    public HistorialAcademico get_historial(String user){
    	for (Alumno alumnoEnLista : alumnos) {
    		if (alumnoEnLista.username == user) {
    			return alumnoEnLista.historialesAcademicos;
    		}
    	}
        return null;
    }

}
