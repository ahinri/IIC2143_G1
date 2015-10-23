import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class PLogin extends Pane {
    Contenido cont=Contenido.getMi_instancia();
    public PLogin() throws IOException {

        Parent a=FXMLLoader.load(getClass().getClassLoader().getResource("FXML/login.fxml"));
        this.getChildren().add(a);

        Button bt_ingresar = (Button)this.lookup("#bt_ingresar");
        Button bt_reg = (Button)this.lookup("#bt_reg");
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

                        ((Node)(e.getSource())).getScene().getWindow().hide();

                        pHistorial=(PHistorial)scene.getRoot();
                        pHistorial.poblarVista();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }

            }
        });

        bt_reg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                    try {
                        PRegistro pRegistro=new PRegistro();
                        Scene scene = new Scene(pRegistro);
                        Stage stg=new Stage();
                        stg.setTitle("Academic Manager");
                        stg.setScene(scene);
                        stg.show();

                        ((Node)(e.getSource())).getScene().getWindow().hide();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }

        });

    }
    public boolean login (String user,String pass){
        //TODO: cambiar a cont.usuarios
        for (Usuario usuarioEnLista : cont.alumnos) {
        	if (usuarioEnLista._username.equals(user) && usuarioEnLista._password.equals(pass) ) {
        			return true;
        	}
        }
        System.out.println("---------------");
        notificarErrorLogin();
        return false;
    }

    public HistorialAcademico get_historial(String user){
        for (Alumno alumnoEnLista : cont.alumnos) {
    		if (alumnoEnLista._username.equals(user)) {
    			return alumnoEnLista.historialesAcademicos.get(0);
    		}
    	}
        return null;
    }

    public void notificarErrorLogin(){
        Text label_error = (Text)this.lookup("#label_error");
        label_error.setText("Usuario o contrasena incorrecto");
    }

}
