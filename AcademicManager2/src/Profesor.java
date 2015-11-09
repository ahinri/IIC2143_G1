import java.util.*;

/**
 * Created by ahinri on 11-10-2015.
 */
public class Profesor extends Usuario {

    Contenido cont = Contenido.getMi_instancia();

    public Profesor(String nombre, int edad, String sexo, String rut,int id_usuario,String username,String password) {
        super(nombre, edad, sexo, rut,id_usuario,username,password);
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

    public void calificar(Semestre semestre, Ramo ramo, double nota)
    {
    	int index = semestre.ramos.indexOf(ramo);
    	semestre.notas.set(index, nota);
    }
}
