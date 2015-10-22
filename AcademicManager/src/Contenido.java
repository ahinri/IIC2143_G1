import java.util.ArrayList;

/**
 * Created by eduardo on 10/22/15.
 */
public class Contenido {
    //SINGLETON
    private static Contenido mi_instancia=null;
    private Contenido(){

    }
    public static Contenido getMi_instancia(){
        if(mi_instancia==null){
            mi_instancia=new Contenido();
        }
        return mi_instancia;
    }


    ArrayList<DescriptorRamo> descriptores=new ArrayList<DescriptorRamo>();
    ArrayList<Ramo> ramos=new ArrayList<Ramo>();
    ArrayList<Profesor> profesores=new ArrayList<Profesor>();
    ArrayList<Alumno> alumnos=new ArrayList<Alumno>();
    ArrayList<Admin> administradores=new ArrayList<Admin>();
    ArrayList<Semestre> semestres=new ArrayList<Semestre>();
    ArrayList<Malla> mallas=new ArrayList<Malla>();
    ArrayList<HistorialAcademico> historiales= new ArrayList<HistorialAcademico>();
    ArrayList<Usuario> usuarios=new ArrayList<Usuario>();

}
