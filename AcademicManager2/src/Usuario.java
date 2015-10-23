import java.util.*;

/**
 * Created by ahinri on 11-10-2015.
 */
public class Usuario {
    String nombre;
    int edad;
    int id_usuario;
    String sexo;
    String rut;
    String _username;
    String _password;

    public Usuario(String nombre,int edad,String sexo,String rut,int id_usuario,String username,String password){
        this.nombre=nombre;
        this.edad=edad;
        this.sexo=sexo;
        this.rut=rut;
        this.id_usuario=id_usuario;
        this._username=username;
        this._password=password;
    }

    public boolean LogIn(String user, String pass)
    {
    	List<String> usuariosRegistrados = new ArrayList<String>(); //en realidad esta guardada
    	List<String> passUsuarios = new ArrayList<String>(); //en realidad esta junto a la info anterior
    	if (usuariosRegistrados.contains(user))
    	{
    		int index = usuariosRegistrados.indexOf(user);
    		if (passUsuarios.get(index) == pass)
    			return true;
    	}
        return false;
    }
}
