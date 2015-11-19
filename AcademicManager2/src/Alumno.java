import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahinri on 11-10-2015.
 */
public class Alumno extends Usuario{

    //para la pr�xima entrega, esta asignacion
    public HistorialAcademico historial= new HistorialAcademico();
    public ArrayList<Malla> mallas= new ArrayList<>();

    public Alumno(String nombre, int edad, String sexo, String rut,int id_usuario,String username,String password) {
        super(nombre, edad, sexo, rut,id_usuario,username,password);

    }

    public void agregarMalla(Malla m){
        mallas.add(m);
    }

    public boolean registrarse(String user,String pass){
        List<String> usersRegistrados = new ArrayList<String>(); //en realidad estan guardados
        List<String> passUsers = new ArrayList<String>(); //en realidad estan guardadas
        if (!usersRegistrados.contains(user))
        {
        	usersRegistrados.add(user);
        	passUsers.add(pass);
        	return true;
        }
        return false;
    }

    public void rellenar_historial(Semestre s){
        this.historial.agregarSemestreHecho(s);


    }

}
