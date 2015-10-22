import java.util.*;

/**
 * Created by ahinri on 11-10-2015.
 */
public class HistorialAcademico {

    Malla malla;
    int sem_actual=0;
    ArrayList<Semestre> semestres=new ArrayList<>();

    public HistorialAcademico(Malla malla){
        this.malla=malla;
    }

    public boolean agregarSemestre(int id_semestre){
        try{
            semestres.add(new Semestre(id_semestre));
            return true;
        }

        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    
    
    public void avance(){
    	ArrayList<Ramo> aux= new ArrayList<>();
        ArrayList<Ramo> hechos= new ArrayList<>();
        ArrayList<Ramo> faltan=new ArrayList<>();

    	for (Semestre s : semestres){
    		for( Ramo r :s.ramos){
    			aux.add(r);
    		}
    	}
    	for(Ramo x:this.malla.ramos){
    		if (aux.contains(x)){
    			hechos.add(x);
    			}
    		else{
    			faltan.add(x);
    			}
    		
    	}
    	System.out.println("Ramos avanzados: "+ hechos);
    	System.out.println("Ramos que faltan: "+faltan);
    	
    	
    	
    	
    }
}
