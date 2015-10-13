import java.util.*;

/**
 * Created by ahinri on 11-10-2015.
 */
public class Profesor extends Usuario {

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
