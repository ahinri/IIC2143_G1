import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane p=new Pane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(p, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        Contenido cont=Contenido.getMi_instancia();

        Connection c = null;
        java.sql.Statement statement1=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ing.db");
            statement1=c.createStatement();

            // creamos los Descriptores de ramos
            String consulta_descriptores="select * from descriptores";
            ResultSet rs_descriptores=statement1.executeQuery(consulta_descriptores);

            while (rs_descriptores.next()){
                String sigla_descriptor= rs_descriptores.getString("sigla");
                int creditos_descriptor=rs_descriptores.getInt("creditos");
                String programa_descriptor=rs_descriptores.getString("programa");
                int id_descriptor=rs_descriptores.getInt("id_ramo");
                cont.descriptores.add(new DescriptorRamo(sigla_descriptor,creditos_descriptor,programa_descriptor,id_descriptor));
            }

            // creamos los Profesores

            String consulta_profesores="select * from usuarios where tipo='profesor'";
            ResultSet rs_profesores=statement1.executeQuery(consulta_profesores);
            while (rs_profesores.next()) {
                String nombre=rs_profesores.getString("nombre");
                int edad=rs_profesores.getInt("edad");
                String sexo=rs_profesores.getString("sexo");
                String rut=rs_profesores.getString("rut");
                int id_usuario=rs_profesores.getInt("id_usuario");
                String username=rs_profesores.getString("username");
                String clave=rs_profesores.getString("clave");
                cont.profesores.add(new Profesor(nombre,edad,sexo,rut,id_usuario,username,clave));

            }

            // creamos los Administradores

            String consulta_admins="select * from usuarios where tipo='admin'";
            ResultSet rs_admins=statement1.executeQuery(consulta_admins);
            while (rs_admins.next()) {
                String nombre=rs_admins.getString("nombre");
                int edad=rs_admins.getInt("edad");
                String sexo=rs_admins.getString("sexo");
                String rut=rs_admins.getString("rut");
                int id_usuario=rs_admins.getInt("id_usuario");
                String username=rs_admins.getString("username");
                String clave=rs_admins.getString("clave");
                cont.administradores.add(new Admin(nombre,edad,sexo,rut,id_usuario,username,clave));

            }

            //creamos los Alumnos

            String consulta_alumnos="select * from usuarios where tipo='alumno'";
            ResultSet rs_alumnos=statement1.executeQuery(consulta_profesores);
            while (rs_profesores.next()) {
                String nombre=rs_alumnos.getString("nombre");
                int edad=rs_alumnos.getInt("edad");
                String sexo=rs_alumnos.getString("sexo");
                String rut=rs_alumnos.getString("rut");
                int id_usuario=rs_alumnos.getInt("id_usuario");
                String username=rs_alumnos.getString("username");
                String clave=rs_alumnos.getString("clave");
                cont.alumnos.add(new Alumno(nombre,edad,sexo,rut,id_usuario,username,clave));

            }

            // creamos los RAMOS
            String consulta_ramos="select * from ramos;";
            ResultSet rs_ramos=statement1.executeQuery(consulta_ramos);
            while (rs_ramos.next()){
                int id_ramo=rs_ramos.getInt("id");
                String horario=rs_ramos.getString("horario");
                String sala=rs_ramos.getString("sala");
                int seccion=rs_ramos.getInt("secion");
                int cupos=rs_ramos.getInt("cupos");
                int anio=rs_ramos.getInt("año");
                int semestre=rs_ramos.getInt("semestre");
                int id_profesor=rs_ramos.getInt("id_profesor");
                String id_alumnos=rs_ramos.getString("alumnos");


                Profesor teacher=null;
                DescriptorRamo desc=null;
                for(Profesor x : cont.profesores){if (x.id_usuario==id_profesor){teacher=x;break;}}
                for(DescriptorRamo x:cont.descriptores){if(x.id_ramo==id_ramo){desc=x;break;}}

                if (teacher!=null & desc!=null){
                    cont.ramos.add(new Ramo(horario,sala,seccion,cupos,anio,semestre,teacher,cont.descriptores.get(0)));
                    System.out.println("Exito al añadir ramo");
                }

                else {System.out.println("Profesor o descriptor no encontrado");}


            }

            // creamos los Semestres con sus ramos y notas, aca da lo mismo que seccion tomo
            String consulta_semestres="Select * from semestres";
            ResultSet rs_semestres=statement1.executeQuery(consulta_semestres);
            while (rs_semestres.next()){
                int id_semestre=rs_semestres.getInt("id_semestre");
                String notas_semestre=rs_semestres.getString("notas");
                String ramos_semestre=rs_semestres.getString("ramos");

                Semestre auxiliar=new Semestre(id_semestre);

                for (String x:ramos_semestre.split(";")){

                    for(Ramo p:cont.ramos){
                        if(p.descriptor.id_ramo==Integer.parseInt(x)){
                            auxiliar.agregarRamo(p);
                            break;
                        }
                    }
                }



                for (String x:notas_semestre.split(";")){
                    auxiliar.notas.add(Double.parseDouble(x));

                }

                cont.semestres.add(auxiliar);
            }

            //creamos las Mallas id_malla = id_alumno

            String consulta_mallas="Select * from mallas;";
            ResultSet rs_mallas=statement1.executeQuery(consulta_mallas);
            while (rs_mallas.next()){
                int max_creditos=rs_mallas.getInt("max_creditos");
                String carrera=rs_mallas.getString("carrera");
                int maximos_reprobados=rs_mallas.getInt("max_ramos_reprobados");
                int id_malla= rs_mallas.getInt("id_malla");
                String id_descriptores=rs_mallas.getString("descriptores_ramos");
                cont.mallas.add(new Malla(max_creditos,carrera,maximos_reprobados,id_malla));
            }

            //creamos los Historiales

            String consulta_historiales="Select * from historial;";
            ResultSet rs_historial=statement1.executeQuery(consulta_historiales);
            while (rs_mallas.next()){
                int id_alumnoh=rs_mallas.getInt("id_alumno");
                String semestresh = rs_mallas.getString("semestres");
                HistorialAcademico hh=null;
                Semestre s=null;

                for (Malla x : cont.mallas){
                    if(x.id_malla==id_alumnoh){
                        hh=new HistorialAcademico(x);
                        break;
                    }
                }
                for (Semestre x:cont.semestres){
                    for(String sh:semestresh.split(";"))
                        if (String.valueOf(x.id_semestre) == sh) {
                            hh.semestres.add(x);
                        }
                }
                cont.historiales.add(hh);
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);

        }
        System.out.println("Opened database successfully");
    }
}
