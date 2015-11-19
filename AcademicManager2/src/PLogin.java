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
        Button bt_ingresar_admin = (Button)this.lookup(("#bt_ingresar_admin"));
        Button bt_ingresar_profesor = (Button)this.lookup(("#bt_ingresar_profesor"));
        TextField tf_user = (TextField)this.lookup("#tf_user");
        TextField tf_pass = (TextField)this.lookup("#tf_pass");


        bt_ingresar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //si el login es exitoso, abrir el historial academico
                Alumno a_ingresando=login(tf_user.getText(),tf_pass.getText());
                if(a_ingresando!=null){
                    try {
                        PHistorial pHistorial=new PHistorial(a_ingresando);
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

        bt_ingresar_admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(loginAdmin(tf_user.getText(), tf_pass.getText())){
                    try {
                        PAdmin pAdmin=new PAdmin(getAdmin(tf_user.getText()));
                        Scene scene = new Scene(pAdmin);
                        Stage stg=new Stage();
                        stg.setTitle("Academic Manager");
                        stg.setScene(scene);
                        stg.show();

                        ((Node)(e.getSource())).getScene().getWindow().hide();

                        pAdmin=(PAdmin)scene.getRoot();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        bt_ingresar_profesor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(loginProfesor(tf_user.getText(), tf_pass.getText())){
                    try {
                        PProfesor pProfesor=new PProfesor(getProfesor(tf_user.getText()));
                        Scene scene = new Scene(pProfesor);
                        Stage stg=new Stage();
                        stg.setTitle("Academic Manager");
                        stg.setScene(scene);
                        stg.show();

                        ((Node)(e.getSource())).getScene().getWindow().hide();

                        pProfesor=(PProfesor)scene.getRoot();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

    }

    public Alumno login (String user,String pass){
        //TODO: cambiar a cont.usuarios
        System.out.println("LOGIN ALUMNO");
        for (Alumno usuarioEnLista : cont.alumnos) {
            System.out.println("USER: "+usuarioEnLista._username);
            System.out.println("PASS: "+usuarioEnLista._password);
        	if (usuarioEnLista._username.equals(user) && usuarioEnLista._password.equals(pass) ) {
        			return usuarioEnLista;
        	}
        }
        System.out.println("---------------");
        notificarErrorLogin();
        return null;
    }

    public HistorialAcademico get_historial(String user,int id_malla){
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

    public boolean loginAdmin (String user,String pass){
        System.out.println("LOGIN ADMIN");
        for (Usuario usuarioEnLista : cont.administradores) {
            System.out.println("USER: "+usuarioEnLista._username);
            System.out.println("PASS: "+usuarioEnLista._password);
            if (usuarioEnLista._username.equals(user) && usuarioEnLista._password.equals(pass) ) {
                return true;
            }
        }
        notificarErrorLogin();
        return false;
    }

    public boolean loginProfesor (String user,String pass){
        System.out.println("LOGIN PROFESOR");
        for (Usuario usuarioEnLista : cont.profesores) {
            System.out.println("USER: "+usuarioEnLista._username);
            System.out.println("PASS: "+usuarioEnLista._password);
            if (usuarioEnLista._username.equals(user) && usuarioEnLista._password.equals(pass) ) {
                return true;
            }
        }
        notificarErrorLogin();
        return false;
    }

    public Admin getAdmin(String user){
        for (Admin admin : cont.administradores) {
            if (admin._username.equals(user)) {
                return admin;
            }
        }
        return null;
    }

    public Profesor getProfesor(String user){
        for (Profesor profesor : cont.profesores) {
            if (profesor._username.equals(user)) {
                return profesor;
            }
        }
        return null;
    }

}
