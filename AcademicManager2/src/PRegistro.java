import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by ahinri on 23-10-2015.
 */
public class PRegistro extends Pane {
    Contenido cont=Contenido.getMi_instancia();

    public PRegistro() throws IOException {
        Parent a= FXMLLoader.load(getClass().getClassLoader().getResource("FXML/registrarse.fxml"));
        this.getChildren().add(a);

        Button btn_registro = (Button)this.lookup("#btn_registro");

        TextField r_nombre = (TextField)this.lookup("#r_nombre");
        TextField r_edad = (TextField)this.lookup("#r_edad");
        TextField r_rut = (TextField)this.lookup("#r_rut");
        TextField r_user = (TextField)this.lookup("#r_user");
        TextField r_pass = (TextField)this.lookup("#r_pass");
        ComboBox combo_malla=(ComboBox)this.lookup("#combo_malla");
        ComboBox combo_sexo=(ComboBox)this.lookup("#combo_sexo");

        for (Malla malla : cont.mallas) {
            combo_malla.getItems().add(malla.facultad +" "+ Integer.toString(malla.id_malla));
        }

        combo_sexo.getItems().add("masculino");
        combo_sexo.getItems().add("femenino");

        combo_malla.getSelectionModel().selectFirst();
        combo_sexo.getSelectionModel().selectFirst();


        btn_registro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                String[] aux=combo_malla.getValue().toString().split(" ");


                registrarse(r_nombre.getText(),
                        Integer.parseInt(r_edad.getText()),
                        combo_sexo.getValue().toString(),
                        r_rut.getText(),
                        r_user.getText(),
                        r_pass.getText(),
                        Integer.parseInt(aux[aux.length-1])

                );

                try {
                    PLogin plogin =new PLogin();
                    Scene scene = new Scene(plogin);
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
/*
    public HistorialAcademico get_historial(String user){
        for (Alumno alumnoEnLista : cont.alumnos) {
            if (alumnoEnLista._username.equals(user)) {
                return alumnoEnLista.historialesAcademicos.get(0);
            }
        }
        return null;
    }*/

    public void registrarse(String nombre,int edad,String sexo_string,String rut ,String user,String password,int id_malla){
        int id_usuario=cont.usuarios.size()+1;


        Alumno nuevo=new Alumno(nombre,edad,sexo_string,rut,id_usuario,user,password);
        nuevo.agregarMalla(cont.mallas.get(id_malla));

        cont.alumnos.add(nuevo);
        cont.usuarios.add(nuevo);



        String query_usuarios="INSERT INTO USUARIOS VALUES('"+nombre+"',"+edad+",'"+sexo_string+"','"+rut+"','"+id_usuario+"','"+"alumno" + "','"+user+"','"+password+"')";
        System.out.println(query_usuarios);
        try {
            cont.cargar.execute(query_usuarios);

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);

        }


    }
}
