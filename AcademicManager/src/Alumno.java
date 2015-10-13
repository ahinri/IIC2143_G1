import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by ahinri on 11-10-2015.
 */
public class Alumno extends Usuario {

    //para la próxima entrega, esta asignacion
    public ArrayList<HistorialAcademico> historialesAcademicos= new ArrayList<HistorialAcademico>();

    public void crearHistorial(Malla malla){
        historialesAcademicos.add(new HistorialAcademico(malla));
    }

    public boolean registrarse(String user,String pass){
        //**insertar metodo de validacion**
        return true;
    }
}
