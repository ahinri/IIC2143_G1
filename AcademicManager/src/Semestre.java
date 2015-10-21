import java.util.*;

/**
 * Created by ahinri on 11-10-2015.
 */
public class Semestre {
    int id_semestre;
    ArrayList<Double> notas;
    ArrayList<Ramo> ramos= new ArrayList<Ramo>();

    public Semestre(){
        notas=new ArrayList<Double>();

    }

    public boolean agregarRamo(Ramo ramo){
        try{
            ramos.add(ramo);
            //el profesor debe calificar al alumno
            notas.add(1.0);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
