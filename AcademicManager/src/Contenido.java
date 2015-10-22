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


    ArrayList<Usuario> usuarios=new ArrayList<>();
}
