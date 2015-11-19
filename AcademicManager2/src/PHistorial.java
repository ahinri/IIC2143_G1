
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.stage.Popup;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

/**
 * Created by ahinri on 22-10-2015.
 */
public class PHistorial extends Pane {
    Contenido cont =Contenido.getMi_instancia();

    HistorialAcademico ha=null;
    Alumno a_actual=null;

    public PHistorial(Alumno a_actual) throws IOException {
        this.a_actual=a_actual;
        this.ha=a_actual.historialesAcademicos.get(0);
        Parent a=FXMLLoader.load(getClass().getClassLoader().getResource("FXML/historial_academico.fxml"));
        this.getChildren().add(a);

        //crear eventos de botones
        Button bt_add_semestre = (Button)this.lookup("#bt_add_semestre");
        Button bt_avance = (Button)this.lookup("#bt_avance");
        Button bt_busca = (Button)this.lookup("#bt_busca");
        Button bt_cambio = (Button)this.lookup("#bt_cambio");

        //ids ramos
        TextField tf_idramo1 = (TextField)this.lookup("#nramo1");
        TextField tf_idramo2 = (TextField)this.lookup("#nramo2");
        TextField tf_idramo3 = (TextField)this.lookup("#nramo3");
        TextField tf_idramo4 = (TextField)this.lookup("#nramo4");
        TextField tf_idramo5 = (TextField)this.lookup("#nramo5");
        TextField tf_idramo6 = (TextField)this.lookup("#nramo6");
        TextField tf_cambio = (TextField)this.lookup("#text_cambio");

        bt_cambio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage dialog = new Stage();
                dialog.initStyle(StageStyle.UTILITY);
                Parent p=new Pane();
                Scene scene = new Scene(p,300,20);
                dialog.setScene(scene);
                dialog.setTitle("Cambio de malla exitoso");
                dialog.show();
            }

        });




        bt_avance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


                try {
                    PAvance pAvance;
                    pAvance = new PAvance(ha);
                    Scene scene = new Scene(pAvance);
                    Stage stg=new Stage();
                    stg.setTitle("Academic Manager");
                    stg.setScene(scene);
                    stg.show();
                    pAvance=(PAvance)scene.getRoot();
                    pAvance.poblarVista();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }




            }

        });

        bt_busca.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


                try {
                    PBusca pRegistro;
                    pRegistro = new PBusca();
                    Scene scene = new Scene(pRegistro);
                    Stage stg=new Stage();
                    stg.setTitle("Academic Manager");
                    stg.setScene(scene);
                    stg.show();

                    pRegistro=(PBusca)scene.getRoot();
                    pRegistro.poblarVista();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }




            }

        });

        bt_add_semestre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                ArrayList<Integer> lista_ids=new ArrayList<Integer>();
                try{
                    lista_ids.add(Integer.parseInt(tf_idramo1.getText()));
                    lista_ids.add(Integer.parseInt(tf_idramo2.getText()));
                    lista_ids.add(Integer.parseInt(tf_idramo3.getText()));
                    lista_ids.add(Integer.parseInt(tf_idramo4.getText()));
                    lista_ids.add(Integer.parseInt(tf_idramo5.getText()));
                    lista_ids.add(Integer.parseInt(tf_idramo6.getText()));

                }catch (Exception exx){

                }
                agregarSemestre(lista_ids);



            }
        });

    }

    public HistorialAcademico get_historial(String user,int id_malla){
        for (Alumno alumnoEnLista : cont.alumnos) {
            if (alumnoEnLista._username.equals(user)) {
                return alumnoEnLista.historialesAcademicos.get(id_malla);
            }
        }
        return null;
    }

    public void poblarVista() throws IOException {
        System.out.print("==========EMPEZO POBLAR VISTAS===========");

        GridPane grid_historial =(GridPane) this.lookup("#grid_historial");
        grid_historial.getChildren().clear();


        int num_sem=0;

        System.out.print("NUM SEMSTRES: ");
        System.out.println(ha.semestres.size());

        for (Semestre sem:ha.semestres){

            System.out.print("NUM RAMOS: ");
            System.out.println(sem.ramos.size());

            for (Ramo ramo:sem.ramos){
                Parent ramo_gui=FXMLLoader.load(getClass().getClassLoader().getResource("FXML/ramo.fxml"));

                Text label_sigla=(Text)ramo_gui.lookup("#label_sigla");
                String sigla=ramo.descriptor.sigla;

                ramo_gui.setStyle("-fx-background-color: blue");



                System.out.println(sigla);
                label_sigla.setText(sigla);

                grid_historial.addColumn(num_sem, ramo_gui);
            }
            num_sem+=1;
        }




    }



    //TODO: no testeado
    public void agregarSemestre(ArrayList<Integer> ramos){
        int size=cont.semestres.size();
        Semestre nuevo= new Semestre(size);
        String notas="";
        String ramos_sql="";
        Ramo r=null;
        for (Integer num: ramos){
            for (Ramo pl:cont.ramos){
                if (pl.descriptor.id_ramo==num){
                    r =pl;
                }
            }
            nuevo.agregarRamo(r);
            notas+="1.0;";
            ramos_sql+=r.descriptor.id_ramo+";";
        }

        ha.semestres.add(nuevo);

        String semestres_sql="";
        for (Semestre kk:ha.semestres){
            semestres_sql+=kk.id_semestre+";";
        }

        try {
            poblarVista();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("--------------SQL----------------");
        String query_semestres="INSERT INTO semestres values("+size+"," +"'"+notas.substring(0,notas.length()-1)+"'"+","+"'"+ramos_sql.substring(0,ramos_sql.length()-1)+"'"+");";
        String query_historiales="UPDATE historial SET semestres="+"'"+semestres_sql.substring(0,semestres_sql.length()-1)+"'"+" WHERE  id_alumno="+ha.malla.id_malla+";";
        try {
            cont.cargar.execute(query_semestres);
            cont.cargar.execute(query_historiales);


        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);

        }

    }



}
