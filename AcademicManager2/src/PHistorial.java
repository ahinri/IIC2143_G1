
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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
        this.ha=a_actual.historial;
        Parent a=FXMLLoader.load(getClass().getClassLoader().getResource("FXML/historial_academico.fxml"));
        this.getChildren().add(a);

        //crear eventos de botones
        Button bt_add_semestre = (Button)this.lookup("#bt_add_semestre");
        Button bt_avance = (Button)this.lookup("#bt_avance");
        Button bt_busca = (Button)this.lookup("#bt_busca");

        //ids ramos
        ComboBox combo_ramo1 = (ComboBox)this.lookup("#combo_ramo1");
        ComboBox combo_ramo2 = (ComboBox)this.lookup("#combo_ramo2");
        ComboBox combo_ramo3 = (ComboBox)this.lookup("#combo_ramo3");
        ComboBox combo_ramo4 = (ComboBox)this.lookup("#combo_ramo4");
        ComboBox combo_ramo5 = (ComboBox)this.lookup("#combo_ramo5");


        for (Ramo r : cont.ramos) {
            combo_ramo1.getItems().add(r.descriptor.sigla +" - NRC: "+ Integer.toString(r.nrc));
        }
        combo_ramo1.getSelectionModel().select(5);

        for (Ramo r : cont.ramos) {
            combo_ramo2.getItems().add(r.descriptor.sigla +" - NRC: "+ Integer.toString(r.nrc));
        }

        for (Ramo r : cont.ramos) {
            combo_ramo3.getItems().add(r.descriptor.sigla +" - NRC: "+ Integer.toString(r.nrc));
        }


        for (Ramo r : cont.ramos) {
            combo_ramo4.getItems().add(r.descriptor.sigla +" - NRC: "+ Integer.toString(r.nrc));
        }


        for (Ramo r : cont.ramos) {
            combo_ramo5.getItems().add(r.descriptor.sigla +" - NRC: "+ Integer.toString(r.nrc));
        }







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
                    lista_ids.add(Integer.parseInt(combo_ramo1.getValue().toString().split(" - NRC: ")[1]));
                    lista_ids.add(Integer.parseInt(combo_ramo2.getValue().toString().split(" - NRC: ")[1]));
                    lista_ids.add(Integer.parseInt(combo_ramo3.getValue().toString().split(" - NRC: ")[1]));
                    lista_ids.add(Integer.parseInt(combo_ramo4.getValue().toString().split(" - NRC: ")[1]));
                    lista_ids.add(Integer.parseInt(combo_ramo5.getValue().toString().split(" - NRC: ")[1]));

                }catch (Exception exx){

                }

                agregarSemestre(lista_ids);



            }
        });

    }
/*
    public HistorialAcademico get_historial(String user,int id_malla){
        for (Alumno alumnoEnLista : cont.alumnos) {
            if (alumnoEnLista._username.equals(user)) {
                return alumnoEnLista.historialesAcademicos.get(id_malla);
            }
        }
        return null;
    }*/

    public void imprimirCampos(){

        System.out.print("HISTORIAL ALUMNO ACTUAL: ");
        System.out.println(a_actual.historial.toString());

        for (Malla m:a_actual.mallas){
            System.out.print("MALLA: ");
            System.out.println(m.toString());
        }
    }

    public void poblarVista() throws IOException {
        System.out.print("==========EMPEZO POBLAR VISTAS===========");

        GridPane grid_historial =(GridPane) this.lookup("#grid_historial");
        grid_historial.getChildren().clear();


        int num_sem=0;



        for (Semestre sem:ha.semestres){

            System.out.print("NUM RAMOS: ");
            System.out.println(sem.ramos.size());

            for (Ramo ramo:sem.ramos){
                Parent ramo_gui=FXMLLoader.load(getClass().getClassLoader().getResource("FXML/ramo.fxml"));

                Text label_sigla=(Text)ramo_gui.lookup("#label_sigla");
                ramo_gui.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        Scene scene=null;
                        try {

                            PPopupRamo ramo_popup=new PPopupRamo(ramo,a_actual);
                            scene=new Scene(ramo_popup);
                            Stage stg=new Stage();
                            stg.setTitle("Programa");
                            stg.setScene(scene);
                            stg.show();

                            ramo_popup=(PPopupRamo)scene.getRoot();
                            ramo_popup.poblarVista();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                });
                String sigla=ramo.descriptor.sigla;

                if(num_sem==ha.semestres.size()-1){
                    ramo_gui.setStyle("-fx-background-color: gold");
                }else{
                    ramo_gui.setStyle("-fx-background-color: green");
                }




                System.out.println(sigla);
                label_sigla.setText(sigla);

                grid_historial.addColumn(num_sem, ramo_gui);
            }
            num_sem+=1;
        }




    }



    //TODO: no testeado



    public boolean agregarSemestre(ArrayList<Integer> nrcs){
        int size=cont.semestres.size()+1;
        Semestre nuevo= new Semestre(size);
        String notas="";
        String ramos_sql="";
        Ramo r=null;
        System.out.print("NRCS: ");
        System.out.println(nrcs);
        for (Integer num: nrcs){
            for (Ramo pl:cont.ramos){
                if (pl.nrc==num){
                    r =pl;
                }
            }
            if(r.cupos<=0){
                //TODO: FEEDBACK VISUAL NO QUEDAN CUPOS
                return false;
            }
            r.cupos-=1;
            nuevo.agregarRamo(r);
            notas+="1.0;";
            ramos_sql+=r.nrc+";";
        }

        ha.semestres.add(nuevo);
        cont.semestres.add(nuevo);

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
        System.out.println(size);
        System.out.println(notas);
        System.out.println(ramos_sql);
        String query_semestres="INSERT INTO semestres values("+size+"," +"'"+notas.substring(0,notas.length()-1)+"'"+","+"'"+ramos_sql.substring(0,ramos_sql.length()-1)+"'"+");";
        String query_historiales="UPDATE historial SET semestres="+"'"+semestres_sql.substring(0,semestres_sql.length()-1)+"'"+" WHERE id_alumno="+a_actual.id_usuario+";";
        try {
            cont.cargar.execute(query_semestres);
            cont.cargar.execute(query_historiales);


        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);

        }
        return true;

    }
    public ArrayList<String[] > obtenerComentarios(int nrc) {
        ArrayList<String[]> answ=new ArrayList<>();
        for(String[] datax:cont.comentarios){
            if (datax[0].equals(Integer.toString(nrc))){
                answ.add(new String[]{datax[1],datax[2]});
            }
        }
        return answ;
    }

    public void guardarComentario(int nrc,String comentario,String usuario) {

        cont.comentarios.add(new String[]{Integer.toString(nrc),comentario,usuario});
        String query_foro="INSERT INTO foro VALUES ("+nrc+", '"+comentario+"'"+",'"+usuario+"');";
        try {
            cont.cargar.execute(query_foro);

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);

        }

    }



}
